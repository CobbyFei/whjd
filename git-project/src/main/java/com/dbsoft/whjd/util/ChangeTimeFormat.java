package com.dbsoft.whjd.util;

//import java.util.Date;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*格式化时间*/
public class ChangeTimeFormat {
	private volatile static ChangeTimeFormat changeTimeFormat;
	private ChangeTimeFormat(){}
	
	public static ChangeTimeFormat getInstance()
	{
		
		if(changeTimeFormat==null)
		{
			synchronized (ChangeTimeFormat.class) {
				changeTimeFormat=new ChangeTimeFormat();
			}
		}
		return changeTimeFormat;
			
	}
	public  Timestamp strToTimeStamp(String timeString) { // string to timestamp
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sdate = df.format(Date.valueOf(timeString));
		return Timestamp.valueOf(sdate);
	}

	public   String timeStampToString(Timestamp timestamp) { // timestamp to
															// string
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式
		String str = df.format(timestamp);
		return str;
	}
	// timestamp to PreciseString
	public String timeStampToPreciseString(Timestamp timestamp) { 		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式
		String str = df.format(timestamp);
		return str;
	}
	//判断该字符串是否是一个符合规则的日期
	public boolean isTimeFormat(String timeString){
		
		Pattern p = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
		Matcher m = p.matcher(timeString.trim());
		return m.matches();
	}
}
