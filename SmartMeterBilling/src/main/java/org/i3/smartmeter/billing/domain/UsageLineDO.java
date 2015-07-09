package org.i3.smartmeter.billing.domain;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.i3.smartmeter.billing.test.DateFormatter;

@Entity
@Table(name="USAGE_INFO",uniqueConstraints={@UniqueConstraint(columnNames="usage_info_id")})
@XmlRootElement(name = "usage")
public class UsageLineDO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usage_info_id")
	int usage_info_id;
	
	@Column(name="SMARTMETER_ID")
	@JsonProperty("sm_identifier")
	Long smartMeterID; 
	
	@Column(name="START_TIME")
	@JsonProperty("st_time")
	Date startTime; 
	
	@JsonProperty("mr_start")
	@Column(name="READING_START")
	Long readingStart; 

	@JsonProperty("end_time")
	@Column(name="END_TIME")
	Date endTime; 

	@JsonProperty("mr_end")
	@Column(name="READING_END")
	Long readingEnd;
	
	private static int i = -1; 
	
	public static final int COL_SMT_ID = ++i;
	public static final int COL_STRT_TM = ++i;
	public static final int COL_STRT_RDNG = ++i;
	public static final int COL_END_TM = ++i;
	public static final int COL_END_RDNG = ++i;
	
	private static final String COL_SEPRTR = ",";
	
	public UsageLineDO(String line) throws ParseException {
		// TODO Auto-generated constructor stub
		this(line.split(COL_SEPRTR));
	}
	
	public UsageLineDO(String[] usageInfo) throws ParseException {
		// TODO Auto-generated constructor stub
		if(usageInfo!=null && usageInfo.length >= i){
			smartMeterID = Long.valueOf(usageInfo[COL_SMT_ID]);
			startTime = DateFormatter.getDate(usageInfo[COL_STRT_TM]);
			endTime = DateFormatter.getDate(usageInfo[COL_END_TM]);
			readingStart = Long.valueOf(usageInfo[COL_STRT_RDNG]);
			readingEnd = Long.valueOf(usageInfo[COL_END_RDNG]);
		}
	}

	public UsageLineDO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getSmartMeterID() {
		return smartMeterID;
	}

	@XmlElement
	public void setSmartMeterID(Long smartMeterID) {
		this.smartMeterID = smartMeterID;
	}

	@JsonSerialize(using=JsonDateDeserializer.class)
	public Date getStartTime() {
		return startTime;
	}

	@XmlElement
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getReadingStart() {
		return readingStart;
	}

	@XmlElement
	public void setReadingStart(Long readingStart) {
		this.readingStart = readingStart;
	}

	@JsonSerialize(using=JsonDateDeserializer.class)
	public Date getEndTime() {
		return endTime;
	}

	@XmlElement
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getReadingEnd() {
		return readingEnd;
	}

	@XmlElement
	public void setReadingEnd(Long readingEnd) {
		this.readingEnd = readingEnd;
	}

	@Override
	public String toString() {
		return "UsageLineDO [smartMeterID=" + smartMeterID + ", startTime="
				+ startTime + ", readingStart=" + readingStart + ", endTime="
				+ endTime + ", readingEnd=" + readingEnd + "]";
	}
}
