package com.dbsoft.whjd.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.pageModel.Json;

public class EXCELUtils {
	public static void main(String[] args) {
		String path="d:/";
		String fileName="test";
		String fileType="xlsx";
		Json res=new Json();
		try {
			res=read2(path, fileName, fileType);
			System.out.println(res.getMsg()+" "+res.isSuccess());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void read(String path,String fileName,String fileType) throws IOException
	{
		InputStream stream=new FileInputStream(path+fileName+"."+fileType);
	    Workbook wb=null;
	    if(fileType.equals("xls") || fileType.equals("xlsx"))
	    {
	    	wb=new HSSFWorkbook(stream);
	    }
	    else {
			System.out.println("输入的文件格式不正确");
		}
	    Sheet sheet=wb.getSheetAt(0);
	    for(Row row:sheet)
	    {
	    	for(Cell cell:row)
	    	{
	    		System.out.print(cell.getNumericCellValue()+"   ");
	    	}
	    	System.out.println();
	    }
	}
	public  static Json read2(String path,String fileName,String fileType) throws IOException
	{
		Json res=new Json();
		String filePath="d:/test.xlsx";
		List<BlackNameList> list=new ArrayList<BlackNameList>();
		InputStream stream=null;;
		try {
			stream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			res.setMsg("文件不存在");
			res.setSuccess(false);
			return res;
		}
		Workbook wb=null;
	    if(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))
	    {
	    	try {
				wb=new HSSFWorkbook(stream);
			} catch (IOException e) {
				e.printStackTrace();
				res.setMsg("打开文件出现异常");
				res.setSuccess(false);
				return res;
			}
	    }
	    else {
	    	System.out.println("输入的文件格式不正确");
	    	res.setSuccess(false); 
	    	res.setMsg("导入的文件格式不正确！只能导入xls或者xlsx文件格式!");
	    	return res;
		}
	    
	    Sheet sheet=wb.getSheetAt(0);
	    try {
	    	int totalNum=sheet.getLastRowNum();
	    	if(totalNum<1)
	    	{
	    		res.setMsg("excel文件中没有数据");
				res.setSuccess(false);
	    	}
	    	else {
	    		for(int i=1;i<=totalNum;i++)
	    		{
	    			BlackNameList bnl=new BlackNameList();
	    			Row row=sheet.getRow(i);
	    			//Cell cell0=row.getCell(0);
	    			Cell cell1=row.getCell(1);
	    			Cell cell2=row.getCell(2);
	    			Cell cell3=row.getCell(3);
	    			Cell cell4=row.getCell(4);
	    			Cell cell5=row.getCell(5);
	    			Cell cell6=row.getCell(6);
	    			Cell cell7=row.getCell(7);
	    			
	    			
	    			//int id=(int) cell0.getNumericCellValue();
	    			String licence=cell1.getStringCellValue(); 
	    			String violationTime=cell2.getStringCellValue();
	    			String violationAddress=cell3.getStringCellValue();
	    			String violationType=cell4.getStringCellValue();
	    			String isPunished=cell5.getStringCellValue();
	    			String isCancel=cell6.getStringCellValue();
	    			String remark=cell7.getStringCellValue();
	    			
	    		//	System.out.println(id);
	    			System.out.println(licence);
	    			System.out.println(violationTime);
	    			System.out.println(violationAddress);
	    			System.out.println(violationType);
	    			System.out.println(isPunished);
	    			System.out.println(isCancel);
	    			System.out.println(remark);
	    			
	    			
	    			bnl.setLicence(licence);
	    			bnl.setViolationTime(Timestamp.valueOf(violationTime));
	    			bnl.setViolationAddress(violationAddress);
	    			if(violationType.trim().equals("违规限行"))
	    			{
	    				bnl.setViolationType("1");
	    			}
	    			else {
						bnl.setViolationType("2");
					}
	    			if(isPunished.trim().equals("否"))
	    			{
	    				bnl.setIsPunished(false);
	    			}
	    			else {
						bnl.setIsPunished(true);
					}
	    			if(isCancel.trim().equals("否"))
	    			{
	    				bnl.setIsCancel(false);
	    			}
	    			else {
						bnl.setIsCancel(true);
					}
	    			bnl.setRemarks(remark);
	    			
	    			
	    			
	    			list.add(bnl);
	    		}
				res.setSuccess(true);
				res.setMsg("导入excel文件成功!");
			}
		} catch (Exception e) {
			res.setMsg("文件中数据格式不正确出现异常");
			res.setSuccess(false);
			e.printStackTrace();
		}
		return res;
	}

}
