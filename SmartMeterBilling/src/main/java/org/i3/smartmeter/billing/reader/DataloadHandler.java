package org.i3.smartmeter.billing.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.i3.smartmeter.billing.dao.UsageInfoDao;
import org.i3.smartmeter.billing.domain.UsageLineDO;
import org.i3.smartmeter.billing.test.DateFormatter;
import org.springframework.stereotype.Service;

@Service
public class DataloadHandler {
	
	private static final long THRESHOLD_SPLIT_AMT = 5L;
	private static final long SINGLE_RECORD_SIZE = 83L;
	private static final long THRESHOLD_DEFAULT = 50;
	private static final long THRESHOLD_DEFAULT_ALL = -1;
	
	private long thresholdIndicator;
	
	private UsageInfoDao usageInfoDao;
	private static final Format formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	
	public DataloadHandler() {
		// TODO Auto-generated constructor stub
		thresholdIndicator = THRESHOLD_DEFAULT;
	}
	
	public void setRunTimeThresholdIndicator(long fileSize){
		long splitSize = fileSize/THRESHOLD_SPLIT_AMT;
		thresholdIndicator = splitSize/SINGLE_RECORD_SIZE;
		if(thresholdIndicator <= 100){thresholdIndicator = THRESHOLD_DEFAULT_ALL;}
		
		System.out.println("File Size: "+fileSize);
		System.out.println("Spliting Into: " +THRESHOLD_SPLIT_AMT);
		System.out.println("Each Split Size: "+splitSize);
		System.out.println("Threshold Indicator: "+thresholdIndicator);
	}

	public void readData(InputStream in) throws IOException, ParseException{

		List<UsageLineDO> usageLineDOs = new LinkedList<UsageLineDO>();
		
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		Cell cell;
		Row row;

		Iterator<Row> rowIterator = sheet.iterator();
		row = rowIterator.next();
		
		int j = 0, k = 0;		
		while (rowIterator.hasNext()) {
			
			if(++k % thresholdIndicator == 0){
				System.out.println(usageLineDOs);
				sendInformation(usageLineDOs, ++j);
				usageLineDOs = new LinkedList<UsageLineDO>();
				k = 0;
			}
			
			row = rowIterator.next();
			UsageLineDO usageLineDO = new UsageLineDO();
			Iterator<Cell> cellIterator = row.cellIterator();
			int i = 0;
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				if(i == UsageLineDO.COL_SMT_ID){
					usageLineDO.setSmartMeterID(new Double(cell.getNumericCellValue()).longValue());
				}else if(i==UsageLineDO.COL_STRT_TM){
					//usageLineDO.setStartTime(new Date(new Double(cell.getNumericCellValue()).longValue()));       
					usageLineDO.setStartTime(DateFormatter.getDate(getXlsFormattedDate(cell), formatter));
				}else if(i==UsageLineDO.COL_END_TM){					
					usageLineDO.setEndTime(DateFormatter.getDate(getXlsFormattedDate(cell), formatter));
				}else if(i==UsageLineDO.COL_STRT_RDNG){
					usageLineDO.setReadingStart(new Double(cell.getNumericCellValue()).longValue());
				}else if(i==UsageLineDO.COL_END_RDNG){
					usageLineDO.setReadingEnd(new Double(cell.getNumericCellValue()).longValue());
				}
				i++;
			}
			usageLineDOs.add(usageLineDO);
		}
		sendInformation(usageLineDOs, ++j);
	}
	
	
	private String getXlsFormattedDate(Cell cell) {
		String formattedDate ="";
		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || DateUtil.isCellDateFormatted(cell)){
			Date date = cell.getDateCellValue();
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss aa");
	        String timeStamp =formatTime.format(date);
	        formattedDate = cell.toString()+ " " +timeStamp;			
		}
		return formattedDate;
	}


	public void readCSVData(InputStream in) throws IOException, ParseException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));		
		List<UsageLineDO> usageLineDOs = new LinkedList<UsageLineDO>();
		
		int i = 0, j = 0;
		br.readLine();
		for(String line; (line = br.readLine()) != null; ) {
			
			if(++i % thresholdIndicator == 0){
				sendInformation(usageLineDOs, ++j);
				usageLineDOs = new LinkedList<UsageLineDO>();
				i = 0;
			}
			
	        UsageLineDO usageLineDO = new UsageLineDO(line);
	        usageLineDOs.add(usageLineDO);
	    }
		sendInformation(usageLineDOs, ++j);
	}
	
	public void sendInformation(final List<UsageLineDO> list, int i){
		System.out.println(list.size());
		//System.out.println(list);
		System.out.println();
		final String name = "Thread-"+i;
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Thread: "+ name +" Starts By : " +new Date());
				//saveUsageLineDO(list);			
				System.out.println("Thread: "+ name +" Ends By : " +new Date());
			}
		}, name).start();
	}
	
	@SuppressWarnings("unused")
	private void saveUsageLineDO(List<UsageLineDO> list) {
		for(UsageLineDO usageLine:list){
			usageInfoDao.save(usageLine);
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\Administrator\\Desktop\\3000_Sample usage file.xls");
		//FileInputStream fin = new FileInputStream();
		//System.out.println(IOUtils.toString(fin, "UTF-8"));
		DataloadHandler handler = new DataloadHandler();
		handler.setRunTimeThresholdIndicator(file.length());
	}

	public void setUsageInfoDao(UsageInfoDao usageInfoDao) {
		this.usageInfoDao = usageInfoDao;
	}

}
