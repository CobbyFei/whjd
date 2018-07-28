package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.pageModel.BlackNameListPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IVehicleLimitRecordService;
import com.dbsoft.whjd.util.DateFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.TrimString;

@Service("vehicleLimitRecordService")
public class VehicleLimitRecordServiceImpl implements IVehicleLimitRecordService{
    private IBaseDao<BlackNameList> vehicleLimitRecordDao;
	@Override
	public Json addVehicleLimitRecord(BlackNameListPage blackNameListPage) {
		Json res=new Json();
		BlackNameList blackNameList=new BlackNameList();
		BeanUtils.copyProperties(blackNameListPage, blackNameList);
		System.out.println(blackNameListPage.getViolationTimePage());
		blackNameList.setViolationTime(Timestamp.valueOf(blackNameListPage.getViolationTimePage()));
		if(blackNameListPage.getIsPunishedPage().equals("1"))
			  blackNameList.setIsPunished(true);
		else {
			blackNameList.setIsPunished(false);
		}
		blackNameList.setIsCancel(false);
		try{
			vehicleLimitRecordDao.save(blackNameList);
			res.setSuccess(true);
		    res.setMsg("添加车辆违规信息成功");
		}catch (Exception e) {
			res.setMsg("添加车辆违规信息失败");
		}
		return res;
	}
	
	@Override
	public DataGrid getAllVechileLimitRecord(BlackNameListPage blackNameListPage) {
		 DataGrid dg=new DataGrid();
		 String hql="from BlackNameList as bnl where 1=1 ";
		 Map<String,Object> tMap=new HashMap<String, Object>();
		
		 if(blackNameListPage.getLicence()!=null)
		 {
			 if(!blackNameListPage.getLicence().trim().equals(""))
			 {
				 String licenceQuery="%"+blackNameListPage.getLicence().trim()+"%";
				 hql+=" and bnl.licence like :licenceQuery";
				 tMap.put("licenceQuery", licenceQuery);
			 }
		 }
		 if(blackNameListPage.getViolationType()!=null)
		 {
			 if(!blackNameListPage.getViolationType().trim().equals("") && !blackNameListPage.getViolationType().trim().equals("0"))
			 {
				 System.out.println("ViolationType的值为:"+blackNameListPage.getViolationType().trim());
				 String typeQuery=blackNameListPage.getViolationType().trim();
				 hql+=" and bnl.violationType=:typeQuery ";
				 tMap.put("typeQuery", typeQuery);
				 
			 }
		 }
		if(blackNameListPage.getIsCancelPage()!=null)
		{
			 if(!blackNameListPage.getIsCancelPage().trim().equals("2"))
			 {
				 System.out.println("isCancelQuery的值为:"+blackNameListPage.getIsCancelPage().trim());
				 String isCancelQuery=blackNameListPage.getIsCancelPage().trim();
				 if(isCancelQuery.equals("0"))
					 tMap.put("isCancelQuery", false);
				 else {
					tMap.put("isCancelQuery", true);
				}
				 hql+=" and bnl.isCancel=:isCancelQuery ";
			 }
		}
		if(blackNameListPage.getIsPunishedPage()!=null)
		{
			 if(!blackNameListPage.getIsPunishedPage().trim().equals("2"))
			 {
				 System.out.println("isPunishedQuery的值为:"+blackNameListPage.getIsPunishedPage().trim());
				 String isPunishedQuery=blackNameListPage.getIsPunishedPage().trim();
				 if(isPunishedQuery.equals("0"))
					 tMap.put("isPunishedQuery", false);
				 else {
					tMap.put("isPunishedQuery", true);
				}
				 hql+=" and bnl.isPunished=:isPunishedQuery ";
			 }
		}
		if(blackNameListPage.getBeginTime()!=null)
		{
			 if(!blackNameListPage.getBeginTime().trim().equals(""))
			 {
				 String beginTimeQuery=blackNameListPage.getBeginTime().trim();
				 tMap.put("beginTimeQuery", Timestamp.valueOf(beginTimeQuery));
				 hql+=" and bnl.violationTime>=:beginTimeQuery ";
			 }
		}
		if(blackNameListPage.getEndTime()!=null)
		{
			 if(!blackNameListPage.getEndTime().trim().equals(""))
			 {
				 String endTimeQuery=blackNameListPage.getEndTime().trim();
				 tMap.put("endTimeQuery", Timestamp.valueOf(endTimeQuery));
				 hql+=" and bnl.violationTime<=:endTimeQuery ";
			 }
		}
			 
         dg.setTotal(vehicleLimitRecordDao.count("select count(*)"+hql,tMap));
		 List<BlackNameList>  list=vehicleLimitRecordDao.find(hql, tMap, blackNameListPage.getPage(), blackNameListPage.getRows());
		 List<BlackNameListPage> nl=new ArrayList<BlackNameListPage>();
		 for(BlackNameList blackname:list)
		 {
			 BlackNameListPage bsp=new BlackNameListPage();
			 BeanUtils.copyProperties(blackname, bsp);
			 if(blackname.getIsCancel()==false)
				 bsp.setIsCancelPage("0");
			 else
			 {
				 bsp.setIsCancelPage("1");
			 }
			 if(blackname.getIsPunished()==false)
			 {
				 bsp.setIsPunishedPage("0");
			 }
			 else {
				bsp.setIsPunishedPage("1");
			}
			bsp.setViolationTimePage(DateFormat.getSimpleDateFormat().format(blackname.getViolationTime()));
			 nl.add(bsp);
		 }
		 dg.setRows(nl);
		 return dg;
		 
		
	}
	
	public IBaseDao<BlackNameList> getVehicleLimitRecordDao() {
		return vehicleLimitRecordDao;
	}
    @Resource(name="baseDao")
	public void setVehicleLimitRecordDao(
			IBaseDao<BlackNameList> vehicleLimitRecordDao) {
		this.vehicleLimitRecordDao = vehicleLimitRecordDao;
	}

	@Override
	public Json cancelVehicleLimitRecord(BlackNameListPage blackNameListPage) {
	    Json res=new Json();
	    String flag="";
		if(blackNameListPage.getIds()!=null)
		{
			try {
				if(!blackNameListPage.getIds().trim().equals(""))
				{
					for(String id:blackNameListPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							String hql="from BlackNameList as bnl where bnl.id=:id ";
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("id", Integer.parseInt(id));
							BlackNameList blackNamelist=vehicleLimitRecordDao.get(hql, tMap);
							blackNamelist.setIsCancel(true);
							vehicleLimitRecordDao.update(blackNamelist);
						}
					}
					res.setSuccess(true);
					res.setMsg("注销限行车辆信息成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("注销id为"+flag+"的车辆信息失败");
				return res;
			}
		}
		return res;
	   
	}

	@Override
	public Json modifyVehicleLimitRecord(BlackNameListPage blackNameListPage) {
		Json res=new Json();
		if(blackNameListPage!=null)
		{
			BlackNameList blackNameList=new BlackNameList();
			BeanUtils.copyProperties(blackNameListPage, blackNameList);
			blackNameList.setViolationTime(Timestamp.valueOf(blackNameListPage.getViolationTimePage()));
			if(blackNameListPage.getIsCancelPage().trim().equals("0"))
			{
				blackNameList.setIsCancel(false);
			}
			else{
				blackNameList.setIsCancel(true);
			}
			if(blackNameListPage.getIsPunishedPage().trim().equals("0"))
			{
				blackNameList.setIsPunished(false);
			}
			else {
				blackNameList.setIsPunished(true);
			}
			try{
			    vehicleLimitRecordDao.update(blackNameList);
			    res.setSuccess(true);
			    res.setMsg("修改车辆限行信息成功");
			}catch (Exception e) {
				e.printStackTrace();
				res.setMsg("修改车辆限行信息失败");
				res.setSuccess(false);
			}
		}
		return res;
	}

	@Override
	public Json deleteVehicleLimitRecord(BlackNameListPage blackNameListPage) {
	    Json res=new Json();
	    String flag="";
		if(blackNameListPage.getIds()!=null)
		{
			try {
				if(!blackNameListPage.getIds().trim().equals(""))
				{
					for(String id:blackNameListPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							String hql="from BlackNameList as bnl where bnl.id=:id ";
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("id", Integer.parseInt(id));
							BlackNameList blackNamelist=vehicleLimitRecordDao.get(hql, tMap);
							vehicleLimitRecordDao.delete(blackNamelist);
						}
					}
					res.setSuccess(true);
					res.setMsg("删除限行车辆信息成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("删除id为"+flag+"的车辆信息失败");
				return res;
			}
		}
		return res;
	}

	@Override
	public Json isBlacKNameExists(BlackNameListPage blackNameListPage) {
		Json res=new Json();
		if(blackNameListPage!=null)
		{
			try {
				blackNameListPage=(BlackNameListPage)TrimString.getInstance().trimObjectString(blackNameListPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(blackNameListPage.getLicence()!=null&&!blackNameListPage.getLicence().equals(""))
			{
				String hql="select count(*) from BlackNameList as bnl where bnl.licence like :licece and bnl.isCancel=false";
				Map<String, Object> tMap=new HashMap<String, Object>();
				tMap.put("licence", "%"+blackNameListPage.getLicence()+"%");
				long counter=vehicleLimitRecordDao.count(hql, tMap);
				if(counter>0)
				{
					res.setSuccess(true);
					res.setMsg("存在违章记录");
				}
				else {
					res.setSuccess(false);
					res.setMsg("不存在违章记录");
				}
			}
			
		}
	   return res;
	}

	@Override
	public Json exportVehicleLimit(BlackNameListPage blackNameListPage) {
		List<Object> pageList = new ArrayList<Object>();
		if (blackNameListPage.getIds() != null) {
			for (String id : blackNameListPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					BlackNameListPage dlp=new BlackNameListPage();
					BlackNameList bsp=vehicleLimitRecordDao.get(BlackNameList.class,Integer.valueOf(id));
					BeanUtils.copyProperties(bsp, dlp);
					 if(bsp.getIsCancel()==false)
						 dlp.setIsCancelPage("0");
					 else
					 {
						 dlp.setIsCancelPage("1");
					 }
					 if(bsp.getIsPunished()==false)
					 {
						 dlp.setIsPunishedPage("0");
					 }
					 else {
						dlp.setIsPunishedPage("1");
					}
					dlp.setViolationTimePage(DateFormat.getSimpleDateFormat().format(bsp.getViolationTime()));
					pageList.add(dlp);
				}
			}
		}
		String filePath="";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
					"formatExcel", "formatTitle", VehicleLimitRecordServiceImpl.class);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(filePath);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		return retJson;
	}
	
	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((BlackNameListPage) obj).getId()+"");
		result.add(((BlackNameListPage) obj).getLicence());
		result.add(((BlackNameListPage) obj).getLicenceColor());
		result.add(((BlackNameListPage) obj).getViolationType().equals("0")?"违规限行":"超标排放");
		result.add(((BlackNameListPage) obj).getViolationTimePage());
		result.add(((BlackNameListPage) obj).getViolationAddress());
		result.add(((BlackNameListPage) obj).getIsPunishedPage().equals("0")?"否":"是");
		result.add(((BlackNameListPage) obj).getIsCancelPage().equals("0")?"否":"是");
		result.add(((BlackNameListPage) obj).getRemarks());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("车牌号");
		result.add("车牌颜色");
		result.add("违规类别");
		result.add("违规时间");
		result.add("违规地点");
		result.add("是否已接受处罚");
		result.add("是否已注销");
		result.add("备注");
		return result;
	}

	@Override
	public Json importVehicleLimit(BlackNameListPage blackNameListPage) {
		Json res=new Json();
		String filePath=blackNameListPage.getFilePath();
		
		System.out.println(filePath);
		
		List<BlackNameList> list=new ArrayList<BlackNameList>();
		InputStream stream=null;
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
				wb=new XSSFWorkbook(stream);
			} catch (Exception e) {
		       try{
		    	   wb=new HSSFWorkbook(stream);
		       }catch (Exception ex) {
		    	    ex.printStackTrace();
					res.setMsg("打开文件出现异常");
					res.setSuccess(false);
					return res;
			   }
			}
	    }
	    else {
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
	    			//Cell cell0=row.getCell(0);  序号不用读取
	    			Cell cell1=row.getCell(1);  //车牌号
	    			Cell cell2=row.getCell(2);  //车牌颜色
	    			Cell cell3=row.getCell(3);   //违规时间
	    			Cell cell4=row.getCell(4);   //违规地点
	    			Cell cell5=row.getCell(5);    //违规类别
	    			Cell cell6=row.getCell(6);   //是否已经接受处罚
	    			Cell cell7=row.getCell(7);   //是否已经注销
	    			Cell cell8=row.getCell(8);   //备注
	    			
	    			
	    			//int id=(int) cell0.getNumericCellValue();
	    			String licence=cell1.getStringCellValue().trim(); 
	    			String licenceColor=cell2.getStringCellValue().trim(); 
	    			String violationTime=cell3.getStringCellValue().trim();
	    			String violationAddress=cell4.getStringCellValue().trim();
	    			String violationType=cell5.getStringCellValue().trim();
	    			String isPunished=cell6.getStringCellValue().trim();
	    			String isCancel=cell7.getStringCellValue().trim();
	    			String remark=cell8.getStringCellValue().trim();
	    			if(licence.equals(""))
	    			{
	    				res.setMsg("第"+(i+1)+"行的车牌号不能为空");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			if(licenceColor.equals(""))
	    			{
	    				res.setMsg("第"+(i+1)+"行的车牌颜色不能为空");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			if(violationTime.equals(""))
	    			{
	    				res.setMsg("第"+(i+1)+"行的违规时间不能为空");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			if(violationAddress.equals(""))
	    			{
	    				res.setMsg("第"+(i+1)+"行的违规地址不能为空");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			if(violationType.equals(""))
	    			{
	    				res.setMsg("第"+(i+1)+"行的违规类别不能为空");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			if(!violationType.equals("违规限行") && !violationType.equals("超标排放"))
	    			{
	    				res.setMsg("第"+(i+1)+"行的违规类别只能是违规限行或者超标排放!");
	    				res.setSuccess(false);
	    				return res;
	    			}
	    			bnl.setLicence(licence);
	    			bnl.setLicenceColor(licenceColor);
	    			bnl.setViolationTime(Timestamp.valueOf(violationTime));
	    			bnl.setViolationAddress(violationAddress);
	    			if(violationType.equals("违规限行"))
	    			{
	    				bnl.setViolationType("1");
	    			}
	    			else {
						bnl.setViolationType("2");
					}
	    			if(isPunished.equals("否"))
	    			{
	    				bnl.setIsPunished(false);
	    			}
	    			else {
						bnl.setIsPunished(true);
					}
	    			if(isCancel.equals("否"))
	    			{
	    				bnl.setIsCancel(false);
	    			}
	    			else {
						bnl.setIsCancel(true);
					}
	    			bnl.setRemarks(remark);
	    			list.add(bnl);
	    		}
			}
		} catch (Exception e) {
			res.setMsg("文件中数据格式不正确出现异常");
			res.setSuccess(false);
			e.printStackTrace();
			return res;
		}finally{
			File file=new File(blackNameListPage.getFilePath());
			file.delete();
		}
	    
	    //向数据库中插入数据
		for(int i=0;i<list.size();i++)
		{
			BlackNameList bnl=list.get(i);
			vehicleLimitRecordDao.save(bnl);
		}
		res.setSuccess(true);
		res.setMsg("导入excel文件成功!");
		
		return res;
	}

	@Override
	public Json copyToExcel(BlackNameListPage blackNameListPage) {
		Json json=new Json();
		if(blackNameListPage.getMyFile()!=null)
		{
			InputStream is=null;
			OutputStream os=null;
			String dir_path="c:/upload";
			File dir=new File(dir_path);
			if(dir.exists()==false)
			{
				if(!dir.mkdir())
				{
					json.setMsg("文件夹创建失败");
					json.setSuccess(false);
					return json;
				}
			}
			String filePath=dir_path+"/"+System.currentTimeMillis()+".xls";
			try {
				os=new FileOutputStream(new File(filePath));
				is=new FileInputStream(blackNameListPage.getMyFile());
			} catch (FileNotFoundException e) {
				json.setMsg("未找到文件");
			    json.setSuccess(false);
				return json;
			}
		    try {
				byte[] b = new byte[400];
				 int length = 0;
				 while ((length = is.read(b)) > 0) {
				  os.write(b, 0, length);
				 }
				 
				 json.setSuccess(true);
				 json.setMsg(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				json.setMsg("文件复制出现异常");
				json.setSuccess(false);
				return json;
			}finally
			{
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		   
		}
		return json;
	}

}
