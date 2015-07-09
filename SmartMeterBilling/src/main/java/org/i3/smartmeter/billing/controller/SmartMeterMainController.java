package org.i3.smartmeter.billing.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.i3.smartmeter.billing.controller.response.ControllerResponse;
import org.i3.smartmeter.billing.controller.response.InvoiceResponse;
import org.i3.smartmeter.billing.controller.response.RefreshPricingResponse;
import org.i3.smartmeter.billing.controller.response.UsageInfoResponse;
import org.i3.smartmeter.billing.dao.CustomerDao;
import org.i3.smartmeter.billing.dao.RateDao;
import org.i3.smartmeter.billing.dao.UsageInfoDao;
import org.i3.smartmeter.billing.domain.Customer;
import org.i3.smartmeter.billing.domain.InvoiceDO;
import org.i3.smartmeter.billing.domain.NoRecordsFoundException;
import org.i3.smartmeter.billing.domain.PricingDO;
import org.i3.smartmeter.billing.domain.UsageLineDO;
import org.i3.smartmeter.billing.reader.DataloadHandler;
import org.i3.smartmeter.billing.test.DateFormatter;
import org.i3.smartmeter.engine.calculation.CalculationFramework;
import org.i3.smartmeter.engine.pricing.PricingByRangeRule;
import org.i3.smartmeter.engine.pricing.PricingRule;
import org.i3.smartmeter.engine.tax.TaxPricingRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unchecked")
@Controller
public class SmartMeterMainController {
	
	@Autowired
	@Qualifier("customerDao")
	CustomerDao customerDao;
	
	@Autowired
	@Qualifier("usageInfoDao")
	UsageInfoDao usageInfoDao;
	
	@Autowired
	@Qualifier("rulesDao")
	private RateDao rateDao;
	
	@RequestMapping(value="/customerDetails/addCustomer.go", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody ControllerResponse addCustomerData(@RequestParam("sm_identifier") Long smartMeterId,
			@RequestParam("firstname") String firstName ,
			@RequestParam("lastname") String lastName,
			@RequestParam("address1") String addressLine1,
			@RequestParam("address2") String addressLine2,
			@RequestParam("city") String city,
			@RequestParam("pincode") Long zipCode){
		
		ControllerResponse response = new ControllerResponse();
		response.setSuccess(true);
		try{
			Customer customer = new Customer(firstName, lastName, smartMeterId, addressLine1, zipCode);
			customerDao.save(customer);
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setErrorMessage("System Error has Occured!!!!");
			response.setDeepErrorMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/customerDetails/getUsageInfo.go", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody UsageInfoResponse getUsageInformation(
			@RequestParam(value="smartMeterID", required=false) String smartMeterIDString,
			@RequestParam(value="fromDate", required=false) String fromDate,
			@RequestParam(value="toDate", required=false) String toDate
		) throws ParseException {
		
		UsageInfoResponse response = new UsageInfoResponse();
		response.setSuccess(true);
		try{
			List<UsageLineDO> usageLineDOs = null;
			if(smartMeterIDString != null){
				Long smartMeterID = Long.valueOf(smartMeterIDString);
				if(fromDate!=null && toDate != null){
					Date from = DateFormatter.getDate(fromDate);
					Date to = DateFormatter.getDate(toDate);	
					usageLineDOs = (List<UsageLineDO>) usageInfoDao.getUsageInformation(smartMeterID, from, to);
				}else {
					usageLineDOs = (List<UsageLineDO>) usageInfoDao.getUsageInformation(smartMeterID);
				}
			}else{
				usageLineDOs = (List<UsageLineDO>) usageInfoDao.findAllUsageLineDO();
			}
			response.setUsageLineDOs(usageLineDOs);
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setErrorMessage("System Error has Occured!!!!");
			response.setDeepErrorMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/customerDetails/getInvoice.go", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody InvoiceResponse getInvoice(
			@RequestParam("smartMeterID") Long smartMeterID,
			@RequestParam("fromDate") String from,
			@RequestParam("toDate") String to
		) throws ParseException {
			
		InvoiceResponse response = new InvoiceResponse();
		response.setSuccess(true);
		try{
			InvoiceDO invoiceDO = getInvoiceDO(smartMeterID, from, to);
			response.setInvoiceDO(invoiceDO);
		}catch(NoRecordsFoundException e){
			response.setErrorMessage(e.getMessage());
			response.setDeepErrorMessage(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setErrorMessage("System Error has Occured!!!!");
			response.setDeepErrorMessage(e.getMessage());
		}
		return response;
	
	}

	private InvoiceDO getInvoiceDO(Long smartMeterID, String from, String to)
			throws ParseException, NoRecordsFoundException {
		Date fromDate = DateFormatter.getDate(from);
		Date toDate = DateFormatter.getDate(to);
		customerDao.setUsageDao(usageInfoDao);
		//new RulingEngine().setSpringRateDao(rateDao);
		Customer customer = customerDao.findBySmartMeterId(smartMeterID);
		UsageLineDO usageLineDO = customerDao.getTotalUnitsUsage(smartMeterID, fromDate, toDate);
		Long totalUnits = usageLineDO.getReadingEnd() - usageLineDO.getReadingStart(); 
		//InvoiceDO invoiceDO = InvoiceHandler.getCustomerInvoice(totalUnits, fromDate, toDate);
		
		InvoiceDO invoiceDO = new InvoiceDO();
		invoiceDO.setFromDate(fromDate);
		invoiceDO.setToDate(toDate);
				
		CalculationFramework pricingEngine = CalculationFramework.getInstance();
		
		List<PricingByRangeRule> rules = (List<PricingByRangeRule>) rateDao.findAllRules();	
		
		List<PricingRule> pricingRules = new LinkedList<PricingRule>();
		pricingRules.addAll(rules);
		pricingEngine.addRules(pricingRules);			
		pricingEngine.setTotalUnits(totalUnits);
		
		List<PricingRule> taxRules = new LinkedList<PricingRule>();
		taxRules.add(new TaxPricingRule());
		pricingEngine.addTaxRules(taxRules);
		
		pricingEngine.doCalculation();

		PricingDO pricingDO = new PricingDO();	
		pricingDO.setTotalPrice(pricingEngine.getTotalPrice());
		pricingDO.setTotalUnits(totalUnits);
		pricingDO.setUnitsPrice(pricingEngine.getUnitsPrice());
		pricingDO.setTaxAmount(pricingEngine.getTaxAmount());
		pricingDO.setRules(pricingEngine.getApplicableRules());
		
		invoiceDO.setPriceDO(pricingDO);
		
		invoiceDO.setCustomer(customer);
		return invoiceDO;
	}
	@RequestMapping(value = "/smartController/refreshPricingRules.go", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody RefreshPricingResponse refreshPricingRules() {
		RefreshPricingResponse response = new RefreshPricingResponse();
		response.setSuccess(true);
		try{
			response.setRules((List<PricingByRangeRule>)rateDao.findAllRules());
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setErrorMessage("System Error has Occured!!!!");
			response.setDeepErrorMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/upload.go", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody ControllerResponse handleFileUpload(@RequestParam("fileUploadPath") MultipartFile file) {
		
		ControllerResponse response = new ControllerResponse();
		response.setSuccess(true);
		try{
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				DataloadHandler handler = new DataloadHandler();
				handler.setRunTimeThresholdIndicator(file.getSize());
				handler.setUsageInfoDao(usageInfoDao);
				if(fileName!=null && fileName.toLowerCase().endsWith("xls")){
					handler.readData(file.getInputStream());					
				}else if(fileName!=null && fileName.toLowerCase().endsWith("csv")){
					handler.readCSVData(file.getInputStream());
				}				
			} else {
				response.setSuccess(false);
				response.setErrorMessage("Please send file with contents...");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setErrorMessage("System Error has Occured!!!!");
			response.setDeepErrorMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/rssfeed", method = RequestMethod.GET)
	public ModelAndView getFeedInRss(
			@RequestParam(value="smartMeterID", required=false) String smartMeterIDString,
			@RequestParam(value="fromDate", required=false) String fromDate,
			@RequestParam(value="toDate", required=false) String toDate) throws ParseException {
		List<UsageLineDO> usageLineDOs = null;
		if(smartMeterIDString != null){
			Long smartMeterID = Long.valueOf(smartMeterIDString);
			if(fromDate!=null && toDate != null){
				Date from = DateFormatter.getDate(fromDate);
				Date to = DateFormatter.getDate(toDate);	
				usageLineDOs = (List<UsageLineDO>) usageInfoDao.getUsageInformation(smartMeterID, from, to);
			}else {
				usageLineDOs = (List<UsageLineDO>) usageInfoDao.getUsageInformation(smartMeterID);
			}
		}else{
			usageLineDOs = (List<UsageLineDO>) usageInfoDao.findAllUsageLineDO();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", usageLineDOs);
 
		return mav;
	}
	
	@RequestMapping(value = "/customerDetails/getInvoicePdf.go", method = RequestMethod.GET)
	public ModelAndView getInvoiceAsPdf(
			@RequestParam("smartMeterID") Long smartMeterID,
			@RequestParam("fromDate") String from,
			@RequestParam("toDate") String to,HttpServletRequest request,HttpServletResponse response
		) throws ParseException {
		InvoiceDO invoiceDO = null;
		try{
			invoiceDO = getInvoiceDO(smartMeterID, from, to);			
		}catch(Exception e){
			e.printStackTrace();			
		}	
		return new ModelAndView("pdfView","invoiceDO",invoiceDO);
	}
}