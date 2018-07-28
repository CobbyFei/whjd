package com.dbsoft.whjd.util;

import java.text.SimpleDateFormat;
/**
 * @author wzr
 *  获得simpledateFormat的单例类
 *  该类还是线程不安全模式，以后改为线程安全模式
 */
public class DateFormat {
	private volatile static SimpleDateFormat simpleDateFormat;
	private DateFormat(){}
	public static SimpleDateFormat getSimpleDateFormat()
	{
		if(simpleDateFormat==null)
		{
			synchronized (SimpleDateFormat.class) {
				simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
		}
		return simpleDateFormat;
	}

}
