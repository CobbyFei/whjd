package com.dbsoft.whjd.util;

public class NumTest {
	
	public static boolean isNum(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
