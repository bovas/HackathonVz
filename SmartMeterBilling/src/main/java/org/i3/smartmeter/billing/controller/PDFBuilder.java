package org.i3.smartmeter.billing.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.i3.smartmeter.billing.domain.Customer;
import org.i3.smartmeter.billing.domain.InvoiceDO;
import org.i3.smartmeter.billing.test.DateFormatter;
import org.i3.smartmeter.engine.pricing.PricingByRangeRule;
import org.i3.smartmeter.engine.rules.Rule;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        InvoiceDO invoiceDO = (InvoiceDO) model.get("invoiceDO");
        
        Paragraph para = new Paragraph("INVOICE");
        para.setAlignment(Element.ALIGN_CENTER);
        Font fnt = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fnt.setStyle(Element.TITLE);
        para.setFont(fnt);
        document.add(para);
        
        PdfPTable tabl = new PdfPTable(2);
        tabl.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabl.setWidthPercentage(60.0f);
        tabl.setWidths(new float[] {2.0f, 2.0f});
        
        //tabl.setSpacingBefore(10);
        
        tabl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tabl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
        Customer customer = invoiceDO.getCustomer();
        if(customer!=null){
	        //document.add(new Paragraph("First Name: "+customer.getCustomerFirstName()));
        	if(customer.getCustomerFirstName()!=null){
        	tabl.addCell("First Name:");
        	tabl.addCell(customer.getCustomerFirstName());
        	}
	        //document.add(new Paragraph("Last Name: "+customer.getCustomerLastName()));
        	if(customer.getCustomerLastName()!=null){
	        tabl.addCell("Last Name:");
        	tabl.addCell(customer.getCustomerLastName());
        	}
        	//document.add(new Paragraph("Smart Meter ID: " + customer.getSmartMeterId().toString()));
        	if(customer.getSmartMeterId()!=null){
        	tabl.addCell("Smart Meter ID:");
        	tabl.addCell(customer.getSmartMeterId().toString());
        	}
        	//document.add(new Paragraph("Address Line1: "+customer.getAddressLine1()));
        	if(customer.getAddressLine1()!=null){
        	tabl.addCell("Address Line1:");
        	tabl.addCell(customer.getAddressLine1());
        	}
        	if(customer.getAddressLine2()!=null){
 	        	//document.add(new Paragraph("Address Line2: "+customer.getAddressLine2()));
        		tabl.addCell("Address Line2:");
            	tabl.addCell(customer.getAddressLine2());
 	        }
        	 
        	//document.add(new Paragraph("Zip Code: "+customer.getZipCode()));
        	if(customer.getZipCode()!=null){
        	tabl.addCell("Zip Code:");
        	tabl.addCell(customer.getZipCode().toString());
        	}
        }
        Format fmtr = new SimpleDateFormat("MM/dd/yyyy");
        String frmDate = DateFormatter.getSQLDate(invoiceDO.getFromDate(),fmtr);
        String toDate = DateFormatter.getSQLDate(invoiceDO.getToDate(),fmtr); 
        
        //document.add(new Paragraph("Time Period: "+ frmDate + " - " + toDate));
        tabl.addCell("Time Period:");
    	tabl.addCell(frmDate+ " - " + toDate);
        
        //document.add(new Paragraph("Total Units: " + invoiceDO.getPriceDO().getTotalUnits()));
    	tabl.addCell("Total Units:");
    	tabl.addCell(String.valueOf(invoiceDO.getPriceDO().getTotalUnits()));
    	
        //document.add(new Paragraph("Units Price: " + invoiceDO.getPriceDO().getUnitsPrice()));
    	tabl.addCell("Units Price:");
    	tabl.addCell(String.valueOf(invoiceDO.getPriceDO().getUnitsPrice()));
    	
        //document.add(new Paragraph("Tax Amount: " + invoiceDO.getPriceDO().getTaxAmount()));
    	tabl.addCell("Tax Amount:");
    	tabl.addCell(String.valueOf(invoiceDO.getPriceDO().getTaxAmount()));
    	
        //document.add(new Paragraph("Total Price: " + invoiceDO.getPriceDO().getTotalPrice()));
    	tabl.addCell("Total Price:");
    	tabl.addCell(String.valueOf(invoiceDO.getPriceDO().getTotalPrice()));
        
        document.add(tabl);
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
          
        cell.setPhrase(new Phrase("Usage Tier Start", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Usage Tier End", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);        
    
        for(Rule rule: invoiceDO.getPriceDO().getRules()){
        	if(rule instanceof PricingByRangeRule){
        		PricingByRangeRule rl = (PricingByRangeRule)rule;
		        table.addCell(String.valueOf(rl.getMinValue()));
		        table.addCell(String.valueOf(rl.getMaxValue()));
		        table.addCell(String.valueOf(rl.getPrice()));
        	}
        }
        
         
        document.add(table);
         
    }
}
