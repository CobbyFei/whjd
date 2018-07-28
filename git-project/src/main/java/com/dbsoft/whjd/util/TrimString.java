package com.dbsoft.whjd.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TrimString {
	/**
	 * 将对象中String属性里多余的空格trim掉
	 * 
	 * @author gsw
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private volatile static TrimString trimString;
	private TrimString(){}
	
	public static TrimString getInstance()
	{
		
		if(trimString==null)
		{
			synchronized (TrimString.class) {
				trimString=new TrimString();
			}
		}
		return trimString;
			
	}
	public  Object trimObjectString(Object obj) throws Exception {
		Class<?> ct = obj.getClass();
		Field[] fields = ct.getDeclaredFields();
		for (Field f : fields) {
			if (f.getType() == String.class) {
				String name = f.getName();
				String firstLetter = name.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + name.substring(1);
				String setMethodName = "set" + firstLetter + name.substring(1);
				Method getMethod = ct.getMethod(getMethodName, new Class[] {});
				Method setMethod = ct.getMethod(setMethodName,
						new Class[] { f.getType() });
				Object value = getMethod.invoke(obj, new Object[] {});
				if (value != null)
					setMethod.invoke(obj, value.toString().trim());
			}
		}
		return obj;
	}
}
