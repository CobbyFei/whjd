package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.CalibrationRecord;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.BlackNameListPage;
import com.dbsoft.whjd.pageModel.CalibrationRecordPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionLineCalibrationService;
import com.dbsoft.whjd.service.IDetectionLineService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.DateFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.TrimString;
@Service("detectionLineCalibrationService")
public class DetectionLineCalibrationServiceImpl implements IDetectionLineCalibrationService{
     private IBaseDao<CalibrationRecord>  calibrationRecordDao;

	public IBaseDao<CalibrationRecord> getCalibrationRecordDao() {
		return calibrationRecordDao;
	}
    @Resource(name="baseDao")
	public void setCalibrationRecordDao(
			IBaseDao<CalibrationRecord> calibrationRecordDao) {
		this.calibrationRecordDao = calibrationRecordDao;
	}
    /**
     * 添加检测线标定记录
     */
	@Override
	public Json addCalibrationRecord(CalibrationRecordPage calibrationRecordPage) {
		Json json=new Json();
		try {
			calibrationRecordPage=(CalibrationRecordPage)TrimString.getInstance().trimObjectString(calibrationRecordPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CalibrationRecord calibrationRecord=new CalibrationRecord();
		BeanUtils.copyProperties(calibrationRecordPage, calibrationRecord);
		calibrationRecord.setCalibrationTime(Timestamp.valueOf(calibrationRecordPage.getCalibrationTimePage()));
		DetectionLine detectionLine=new DetectionLine();
		detectionLine.setLineId(calibrationRecordPage.getLineId());
		calibrationRecord.setDetectionLine(detectionLine);
		SysUser sysUser=new SysUser();
		sysUser.setUserId(calibrationRecordPage.getUserId());
		calibrationRecord.setSysUser(sysUser);
		calibrationRecord.setStatus(0);
		try {
			calibrationRecordDao.save(calibrationRecord);
			json.setSuccess(true);
			json.setMsg("添加检测线记录成功");
			
		} catch (Exception e) {
			json.setMsg("添加检测线标定记录不存在");
		}
		return json;
	}
	/**
	 * 删除检测线标定记录
	 */
	@Override
	public Json deleteCalibrationRecord(
			CalibrationRecordPage calibrationRecordPage) {
		Json res=new Json();
		String  flag="";
		if(calibrationRecordPage.getIds()!=null && !calibrationRecordPage.getIds().trim().equals(""))
		{
			try {
				for(String id:calibrationRecordPage.getIds().trim().split(","))
				{
					flag=id;
					String hql="FROM CalibrationRecord as cr where cr.recordId=:recordId";
					Map<String, Object> tMap=new HashMap<String, Object>();
					tMap.put("recordId", Integer.parseInt(id));
					CalibrationRecord cr=calibrationRecordDao.get(hql,tMap);
					calibrationRecordDao.delete(cr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("删除id为"+flag+"的检测线标定记录失败");
				return res;
			}
			res.setSuccess(true);
			res.setMsg("删除检测线标定记录成功");
		}
	    return res;
	}
	@Override
	public DataGrid getAllCalibrationRecord(
			CalibrationRecordPage calibrationRecordPage) {
		try {
			calibrationRecordPage=(CalibrationRecordPage)TrimString.getInstance().trimObjectString(calibrationRecordPage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DataGrid dg=new DataGrid();
		String hql="from CalibrationRecord as cr where 1=1 ";
		Map<String, Object> tMap=new HashMap<String, Object>();
		//动态地拼接sql查询语句
		if(calibrationRecordPage.getStationName()!=null && !calibrationRecordPage.getStationName().equals(""))
		{
			hql+=" and cr.detectionLine.inspectionStation.stationName like :stationName ";
			tMap.put("stationName","%"+calibrationRecordPage.getStationName()+"%");
		}
		if(calibrationRecordPage.getLineName()!=null && !calibrationRecordPage.getLineName().equals(""))
		{
		   hql+=" and cr.detectionLine.lineName like :lineName ";
		   tMap.put("lineName", "%"+calibrationRecordPage.getLineName()+"%");
		}
		if(calibrationRecordPage.getUserId()!=null)
		{
			hql+=" and cr.sysUser.userId=:userId";
			tMap.put("userId", calibrationRecordPage.getUserId());
		}
		if(calibrationRecordPage.getBeginTime()!=null && !calibrationRecordPage.getBeginTime().equals(""))
		{
			hql+=" and cr.calibrationTime >=:beginTime ";
			tMap.put("beginTime", Timestamp.valueOf(calibrationRecordPage.getBeginTime()));
		}
		if(calibrationRecordPage.getEndTime()!=null && !calibrationRecordPage.getEndTime().equals(""))
		{
			hql+=" and cr.calibrationTime <=:endTime ";
			tMap.put("endTime", Timestamp.valueOf(calibrationRecordPage.getEndTime()));
		}
		if(calibrationRecordPage.getStatus()!=null && calibrationRecordPage.getStatus()!=2)
		{
			hql+=" and cr.status =:status ";
			tMap.put("status", calibrationRecordPage.getStatus());
		}
       //获取总数
		dg.setTotal(calibrationRecordDao.count("select count(*) "+hql,tMap));
		
		List<CalibrationRecord> list=calibrationRecordDao.find(hql,tMap,calibrationRecordPage.getPage(),calibrationRecordPage.getRows());
		List<CalibrationRecordPage> dplist=new ArrayList<CalibrationRecordPage>();
		
		for(CalibrationRecord cr:list)
		{
			try {
				cr=(CalibrationRecord)TrimString.getInstance().trimObjectString(cr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			CalibrationRecordPage crPage=new CalibrationRecordPage();
			BeanUtils.copyProperties(cr, crPage);
			crPage.setCalibrationTimePage(ChangeTimeFormat.getInstance().timeStampToPreciseString(cr.getCalibrationTime()));
			crPage.setLineId(cr.getDetectionLine().getLineId());
			crPage.setLineName(cr.getDetectionLine().getLineName().trim());
			crPage.setUserId(cr.getSysUser().getUserId());
			crPage.setUserName(cr.getSysUser().getUserName().trim());
			crPage.setStationId(cr.getDetectionLine().getInspectionStation().getStationId());
			crPage.setStationName(cr.getDetectionLine().getInspectionStation().getStationName().trim());
			dplist.add(crPage);
		}
		dg.setRows(dplist);
		
		return dg;
	}
	/**
	 * 修改检测线的标定记录
	 */
	@Override
	public Json modifyCalibrationRecord(
			CalibrationRecordPage calibrationRecordPage) {
		Json json=new Json();
		if(calibrationRecordPage!=null)
		{
			try {
				calibrationRecordPage=(CalibrationRecordPage)TrimString.getInstance().trimObjectString(calibrationRecordPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String hql="from CalibrationRecord as cr where cr.recordId="+calibrationRecordPage.getRecordId();
			CalibrationRecord calibrationRecord=calibrationRecordDao.find(hql).get(0);
			BeanUtils.copyProperties(calibrationRecordPage, calibrationRecord);
			calibrationRecord.setCalibrationTime(Timestamp.valueOf(calibrationRecordPage.getCalibrationTimePage()));
			DetectionLine dl=new DetectionLine();
			dl.setLineId(calibrationRecordPage.getLineId());
			calibrationRecord.setDetectionLine(dl);
			SysUser sysUser=new SysUser();
			if(calibrationRecordPage.getUserId()!=null)
			{
			    sysUser.setUserId(calibrationRecordPage.getUserId());
			}
			else
			{
				sysUser.setUserId(calibrationRecordPage.getUserId1());
			}
			calibrationRecord.setSysUser(sysUser);
			try{
				calibrationRecordDao.update(calibrationRecord);
				json.setSuccess(true);
				json.setMsg("修改检测信息信息成功");
			}catch (Exception e) {
				json.setMsg("修改检测线标定记录失败");
				json.setSuccess(true);
			}
			
			
		}
		return json;
	}
	@Override
	public Json cancelCalibrationRecord(
			CalibrationRecordPage calibrationRecordPage) {
        Json res=new Json();
        String flag="";
        try {
			calibrationRecordPage=(CalibrationRecordPage)TrimString.getInstance().trimObjectString(calibrationRecordPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(calibrationRecordPage.getIds()!=null && !calibrationRecordPage.getIds().equals(""))
        {
        	try {
				for(String id:calibrationRecordPage.getIds().split(","))
				{
					if(id!=null && !id.trim().equals(""))
					{
						flag=id;
						String hql="from CalibrationRecord as cr where cr.recordId=:recordId ";
						Map<String, Object> tMap=new HashMap<String, Object>();
						tMap.put("recordId", Integer.parseInt(id));
						CalibrationRecord calibrationRecord=calibrationRecordDao.get(hql, tMap);
						calibrationRecord.setStatus(1);
						calibrationRecordDao.update(calibrationRecord);
					}
					res.setSuccess(true);
					res.setMsg("注销检测线标定记录成功");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("注销id为"+flag+"的标定信息失败");
				return res;
			}
        }
        	
		return res;
	}
	@Override
	public Json exportLineCarlibration(
			CalibrationRecordPage calibrationRecordPage) {
		List<Object> pageList = new ArrayList<Object>();
		if (calibrationRecordPage.getIds() != null) {
			for (String id : calibrationRecordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					BlackNameListPage dlp=new BlackNameListPage();
					CalibrationRecord cr=calibrationRecordDao.get(CalibrationRecord.class,Integer.valueOf(id));
					CalibrationRecordPage crPage=new CalibrationRecordPage();
					BeanUtils.copyProperties(cr, crPage);
					crPage.setCalibrationTimePage(ChangeTimeFormat.getInstance().timeStampToPreciseString(cr.getCalibrationTime()));
					crPage.setLineId(cr.getDetectionLine().getLineId());
					crPage.setLineName(cr.getDetectionLine().getLineName().trim());
					crPage.setUserId(cr.getSysUser().getUserId());
					crPage.setUserName(cr.getSysUser().getUserName().trim());
					crPage.setStationId(cr.getDetectionLine().getInspectionStation().getStationId());
					crPage.setStationName(cr.getDetectionLine().getInspectionStation().getStationName().trim());
					pageList.add(crPage);
				}
			}
		}
		String filePath="";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
					"formatExcel", "formatTitle", DetectionLineCalibrationServiceImpl.class);
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
		result.add(((CalibrationRecordPage) obj).getRecordId().toString());
		result.add(((CalibrationRecordPage) obj).getStationId().toString());
		result.add(((CalibrationRecordPage) obj).getStationName());
		result.add(((CalibrationRecordPage) obj).getLineId().toString());
		result.add(((CalibrationRecordPage) obj).getLineName());
		result.add(((CalibrationRecordPage) obj).getUserName());
		result.add(((CalibrationRecordPage) obj).getCalibrationTimePage());
		
		result.add(((CalibrationRecordPage) obj).getNostandardValue().toString());
		result.add(((CalibrationRecordPage) obj).getNoafterValue().toString());
		result.add(((CalibrationRecordPage) obj).getHcstandardValue().toString());
		result.add(((CalibrationRecordPage) obj).getHcafterValue().toString());
		result.add(((CalibrationRecordPage) obj).getCostandardValue().toString());
		result.add(((CalibrationRecordPage) obj).getCoafterValue().toString());
		result.add(((CalibrationRecordPage) obj).getCo2standardValue().toString());
		result.add(((CalibrationRecordPage) obj).getCo2afterValue().toString());

		result.add(((CalibrationRecordPage) obj).getStatus()==0?"正常":"注销");
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("检测站编号");
		result.add("检测站名称");
		result.add("检测线编号");
		result.add("检测线名称");
		result.add("标定人");
		result.add("标定时间");
		result.add("氮氧化物标准值");
		result.add("氮氧化物标定后值");
		result.add("碳氢化合物标准值");
		result.add("碳氢化合物标定后值");
		result.add("一氧化碳标准值");
		result.add("一氧化碳标定后值");
		result.add("二氧化碳标准值");
		result.add("二氧化碳标定后值");
		result.add("状态");
		return result;
	}
     
}
