package com.dbsoft.whjd.test;

public class TestPath {
	public static void main(String[] args) {
		 String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	     System.out.println(path);
	}
}
