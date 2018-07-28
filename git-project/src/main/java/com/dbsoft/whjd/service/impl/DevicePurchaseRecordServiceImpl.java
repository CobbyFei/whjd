package com.dbsoft.whjd.service.impl;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator; 
import java.sql.Date;
import javax.annotation.Resource;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.DevicePurchaseRecord;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.DevicePurchaseRecordPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDevicePurchaseRecordService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.ExportUtil;
@Service("devicePurchaseRecordService")
public class DevicePurchaseRecordServiceImpl implements IDevicePurchaseRecordService {
	private IBaseDao<DevicePurchaseRecord> devicePurchaseRecordDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}
	public IBaseDao<DevicePurchaseRecord> getDevicePurchaseRecordDao() {
		return devicePurchaseRecordDao;
	}
	@Resource(name = "baseDao")
	public void setDevicePurchaseRecordDao(IBaseDao<DevicePurchaseRecord> devicePurchaseRecordDao) {
		this.devicePurchaseRecordDao = devicePurchaseRecordDao;
	}
	@Override
	/*获取设备采购记录*/
	public DataGrid getDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage) {
		String hql = "FROM DevicePurchaseRecord r where 1=1";
		Map<String, Object> tMap = new HashMap<String, Object>();
		//获取session
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession=req.getSession();
		String userStationName=(String) hSession.getAttribute("stationName");
		System.out.println(userStationName);
		DataGrid dg = new DataGrid();
		if(devicePurchaseRecordPage.getBeforePurchaseTime()!=null && !devicePurchaseRecordPage.getBeforePurchaseTime().trim().equals("")){
			 Timestamp beforePurchaseTime=ChangeTimeFormat.getInstance().strToTimeStamp(devicePurchaseRecordPage.getBeforePurchaseTime());
			 tMap.put("beforePurchaseTime", beforePurchaseTime);
			 hql+=" and r.purchaseTime >=:beforePurchaseTime";
		}
		if(devicePurchaseRecordPage.getAfterPurchaseTime()!=null && !devicePurchaseRecordPage.getAfterPurchaseTime().trim().equals("")){
			Timestamp afterPurchaseTime=ChangeTimeFormat.getInstance().strToTimeStamp(devicePurchaseRecordPage.getAfterPurchaseTime());
			tMap.put("afterPurchaseTime", afterPurchaseTime);
			hql+=" and r.purchaseTime <=:afterPurchaseTime";
		}
		if (!userStationName.trim().equals("市局")&&!userStationName.trim().equals("")) {
			tMap.put("stationName", userStationName);
			hql+=" and r.inspectionStation.stationName=:stationName";
		}
		if (devicePurchaseRecordPage.getInspectionStationId()!=null&&userStationName.trim().equals("市局")) {
			//InspectionStation iStation=new InspectionStation();
			//iStation.setStationId(twoSpeedIdleMethodPage.getInspectionStationId());
			tMap.put("stationId", devicePurchaseRecordPage.getInspectionStationId());
			hql+=" and r.inspectionStation.stationId=:stationId";
		}
		if (devicePurchaseRecordPage.getInspectionStationId()!=null&&devicePurchaseRecordPage.getUserStationName().trim().equals("市局"))	
		{
	     Integer stationId=devicePurchaseRecordPage.getInspectionStationId();
	     tMap.put("stationId", stationId);
		 hql+=" and r.inspectionStation.stationId=:stationId";	
		}
		dg.setTotal(devicePurchaseRecordDao.count("SELECT COUNT(*) " + hql, tMap));
		hql+=" order by r.id desc";
		List<DevicePurchaseRecord> l = devicePurchaseRecordDao.find(hql, tMap,devicePurchaseRecordPage.getPage(),devicePurchaseRecordPage.getRows());
		List<DevicePurchaseRecordPage> nl = new ArrayList<DevicePurchaseRecordPage>();
		for (DevicePurchaseRecord store : l) { 
			DevicePurchaseRecordPage rsp = new DevicePurchaseRecordPage();
			BeanUtils.copyProperties(store, rsp);
			if(store.getInspectionStation()!=null)
			   rsp.setInspectionStationId(store.getInspectionStation().getStationId());
			if(store.getInspectionStation()!=null)
			   rsp.setStationName(store.getInspectionStation().getStationName());
			if(store.getPurchaseTime()!=null)
			   rsp.setSelectPurchaseTime(ChangeTimeFormat.getInstance().timeStampToString(store.getPurchaseTime()));
			nl.add(rsp);
		}
		dg.setRows(nl);
		return dg;
	}
	@Override
	/*更新设备采购记录*/
	public Json updateDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage) {
		
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		//ChangeTimeFormat changeTimeFormat=new ChangeTimeFormat();
		try {
			tMap.put("recordId", devicePurchaseRecordPage.getRecordId());
			String hql="FROM DevicePurchaseRecord r where r.recordId =:recordId ";
			DevicePurchaseRecord devicePurchaseRecord=devicePurchaseRecordDao.get(hql, tMap);
			devicePurchaseRecord.setDeviceName(devicePurchaseRecordPage.getDeviceName().trim());
			devicePurchaseRecord.setDeviceModel(devicePurchaseRecordPage.getDeviceModel().trim());
			devicePurchaseRecord.setSpecification(devicePurchaseRecordPage.getSpecification().trim());
			devicePurchaseRecord.setManufacturer(devicePurchaseRecordPage.getManufacturer().trim());
			devicePurchaseRecord.setPurchaseNum(devicePurchaseRecordPage.getPurchaseNum());
			devicePurchaseRecord.setPurchaseTime(ChangeTimeFormat.getInstance().strToTimeStamp(devicePurchaseRecordPage.getSelectPurchaseTime()));
			devicePurchaseRecord.setRemarks(devicePurchaseRecordPage.getRemarks());
			devicePurchaseRecordDao.update(devicePurchaseRecord);
			json.setMsg("更新采购设备记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
		    json.setMsg("更新采购设备记录失败！");
		}
		return json;
	}

	@Override
	/*删除设备采购记录*/
	public Json deleteDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage) {
	    Json json=new Json();
	    String flag="";
		if(devicePurchaseRecordPage.getIds()!=null)
		{
			try {
				if(!devicePurchaseRecordPage.getIds().trim().equals(""))
				{
					for(String id:devicePurchaseRecordPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("recordId", Integer.parseInt(id));
							String hql="from DevicePurchaseRecord as d where d.recordId=:recordId ";
							DevicePurchaseRecord dpr=devicePurchaseRecordDao.get(hql, tMap);
							devicePurchaseRecordDao.delete(dpr);
						}
					}
					json.setSuccess(true);
					json.setMsg("删除设备购买记录成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("删除recordId为"+flag+"的设备购买记录失败");
				return json;
			}
		}
		return json;
	}

	@Override
	public Json addDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage) {
		// TODO Auto-generated method stub
		/*初始化检测站对象*/
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession=req.getSession();
		String userStationName=(String) hSession.getAttribute("stationName");
		System.out.println(hSession.getAttribute("stationName"));
		Map<String, Object> tMap = new HashMap<String, Object>();
		InspectionStation inspectionStation=new InspectionStation();
		if (devicePurchaseRecordPage.getInspectionStationId()!=null) {
			inspectionStation.setStationId(devicePurchaseRecordPage.getInspectionStationId());
		}else {
			if (userStationName!=null&&!userStationName.trim().equals("")) {
				tMap.put("stationName", userStationName.trim());
				String hqlString="FROM InspectionStation as r where r.stationName=:stationName";
				inspectionStation=inspectionStationDao.get(hqlString, tMap);
			}		
		}		
		DevicePurchaseRecord dpRecord=new DevicePurchaseRecord();
		Json json=new Json();

		BeanUtils.copyProperties(devicePurchaseRecordPage, dpRecord);
		dpRecord.setInspectionStation(inspectionStation);
		dpRecord.setPurchaseTime(ChangeTimeFormat.getInstance().strToTimeStamp(devicePurchaseRecordPage.getSelectPurchaseTime()));
		try {
			devicePurchaseRecordDao.save(dpRecord);
			json.setMsg("添加设备购买记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
		    json.setMsg("添加设备购买记录失败！");
		}
		return json;
		
	}
	@Override
	public List<DevicePurchaseRecord> datagrid(int page, int rows) {
		return devicePurchaseRecordDao.find("FROM DevicePurchaseRecord", page, rows);
	}
	/**
	 * 导出设备采购记录
	 */
	
	@Override
	public Json exportDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage) {
		List<Object> pageList = new ArrayList<Object>();
		if (devicePurchaseRecordPage.getIds() != null) {
			for (String id : devicePurchaseRecordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					DevicePurchaseRecordPage dlp=new DevicePurchaseRecordPage();
					DevicePurchaseRecord dl=devicePurchaseRecordDao.get(DevicePurchaseRecord.class,Integer.valueOf(id));
					BeanUtils.copyProperties(dl, dlp,new String[]{"status"});
					dlp.setSelectPurchaseTime(ChangeTimeFormat.getInstance().timeStampToString(dl.getPurchaseTime()));
                    dlp.setStationName(dl.getInspectionStation().getStationName().trim());
					pageList.add(dlp);
				}
			}
		}
		String filePath="";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
					"formatExcel", "formatTitle", DevicePurchaseRecordServiceImpl.class);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(filePath);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		retJson.setMsg("导出采购设备记录成功!");
		return retJson;
	}
	
	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((DevicePurchaseRecordPage) obj).getRecordId().toString());
		result.add(((DevicePurchaseRecordPage) obj).getDeviceName());
		result.add(((DevicePurchaseRecordPage) obj).getStationName());
		result.add(((DevicePurchaseRecordPage) obj).getManufacturer());
		result.add(((DevicePurchaseRecordPage) obj).getDeviceModel());
		result.add(((DevicePurchaseRecordPage) obj).getSpecification());
		result.add(((DevicePurchaseRecordPage) obj).getPurchaseNum().toString());
		result.add(((DevicePurchaseRecordPage) obj).getSelectPurchaseTime());
		//result.add(((DevicePurchaseRecordPage) obj).getStatus());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("编号");
		result.add("设施名称");
		result.add("所属核发点");
		result.add("制造厂商");
		result.add("型号");
		result.add("规格");
		result.add("采购数量");
		result.add("采购日期");
		
		//result.add("状态");
		return result;
	}
}
