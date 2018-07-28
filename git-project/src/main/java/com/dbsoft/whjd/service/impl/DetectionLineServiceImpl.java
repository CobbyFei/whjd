package com.dbsoft.whjd.service.impl;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sound.sampled.Line;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.CalibrationRecord;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.DeviceInfo;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.DeviceInfoPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionLineService;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.util.ExportUtil;

@Service("detectionLineService")
public class DetectionLineServiceImpl implements IDetectionLineService{
    private IBaseDao<DetectionLine> detectionLineDao;
    private IBaseDao<CalibrationRecord> calibrationDao;
    private IBaseDao<SysUser> sysUserDao;
    private IHuanBaoBuService huanBaoBuService;
	
	

	public IHuanBaoBuService getHuanBaoBuService() {
		return huanBaoBuService;
	}
	@Autowired
	public void setHuanBaoBuService(IHuanBaoBuService huanBaoBuService) {
		this.huanBaoBuService = huanBaoBuService;
	}

	/**
     * 添加检测线信息
     */
	@Override
	public Json addDetectionLine(DetectionLinePage detectionLinePage) {
		System.out.println(detectionLinePage.getStationId());
		Json json=new Json();
		DetectionLine detectionLine=new DetectionLine();
		BeanUtils.copyProperties(detectionLinePage, detectionLine);
		InspectionStation inspectionStation=new InspectionStation();
		inspectionStation.setStationId(detectionLinePage.getStationId());
		detectionLine.setInspectionStation(inspectionStation);
		detectionLine.setIsPush(0);
		try{
		   detectionLineDao.save(detectionLine);
		   json.setSuccess(true);
		   json.setMsg("添加检测线信息成功");
		}catch (Exception e) {
		   json.setMsg("检测站不存在,请从下拉列表选择");
		}
		//同步数据
		/*if(json.isSuccess())
			try {
				huanBaoBuService.addDetectionLine(detectionLine);
				json.setMsg(json.getMsg()+"，数据上传成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				json.setMsg(json.getMsg()+"数据上传出现异常，此条数据将会在网络连通时上传.");
			}*/
		return json;
	}
	
	public IBaseDao<DetectionLine> getDetectionLineDao() {
		return detectionLineDao;
	}
	@Resource(name="baseDao")
	public void setDetectionLineDao(IBaseDao<DetectionLine> detectionLineDao) {
		this.detectionLineDao = detectionLineDao;
	}
	public IBaseDao<CalibrationRecord> getCalibrationDao() {
		return calibrationDao;
	}
    @Resource(name="baseDao")
	public void setCalibrationDao(IBaseDao<CalibrationRecord> calibrationDao) {
		this.calibrationDao = calibrationDao;
	}
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name="baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Override
	public Json hasLineName(DetectionLinePage detectionLinePage) {
		Json json=new Json();
		String  hql="from DetectionLine as dl where dl.inspectionStation.stationId=:stationId and dl.lineName=:lineName";
		Map<String, Object> tMap=new HashMap<String, Object>();
		tMap.put("stationId", detectionLinePage.getStationId());
		tMap.put("lineName", detectionLinePage.getQ());
		List<DetectionLine> list=detectionLineDao.find(hql, tMap);
	    if(detectionLinePage.getLineId()!=null)
	    {
	    	if(list!=null && list.size()>0)
	    	{
	    		DetectionLine dl=list.get(0);
	    		if(dl.getLineId()==detectionLinePage.getLineId())
	    		{
	    			json.setSuccess(false);
	    			json.setMsg("名称可用");
	    		}
	    		else {
	    			json.setSuccess(true);
	    			json.setMsg("名称不可用");
				}
	    	}
	    }
	    else if(list!=null && list.size()>0)
		{
			json.setSuccess(true);
			json.setMsg("名称不可用");
		}
		else {
			json.setSuccess(false);
			json.setMsg("名称可用");
		}
	    return json;
	}
	/**
	 * 判断是否存在检测线站内编号
	 */
	@Override
	public Json hasLocaleId(DetectionLinePage detectionLinePage) {
		Json json=new Json();
		String  hql="from DetectionLine as dl where dl.inspectionStation.stationId=:stationId ";
		Map<String, Object> tMap=new HashMap<String, Object>();
		tMap.put("stationId", detectionLinePage.getStationId());
		if(!detectionLinePage.getQ().trim().equals(""))
		{
		     tMap.put("localeId", Integer.parseInt(detectionLinePage.getQ()));
		     hql+=" and dl.localeId=:localeId";
		}
		List<DetectionLine> list=detectionLineDao.find(hql, tMap);
		if(detectionLinePage.getLineId()!=null)
	    {
	    	if(list!=null && list.size()>0)
	    	{
	    		DetectionLine dl=list.get(0);
	    		if(dl.getLineId()==detectionLinePage.getLineId())
	    		{
	    			json.setSuccess(false);
	    			json.setMsg("站内编号可用");
	    		}
	    		else {
	    			json.setSuccess(true);
	    			json.setMsg("站内编号不可用");
				}
	    	}
	    }
		else if(list!=null && list.size()>0)
		{
			json.setSuccess(true);
			json.setMsg("站内编号不可用");
		}
		else {
			json.setSuccess(false);
			json.setMsg("站内编号可用");
		}
	    return json;
	}
   /**
    * 获取所有的检测线信息
    */
	@Override
	public DataGrid getAllDetectionLine(DetectionLinePage detectionLinePage) {
		DataGrid dg=new DataGrid();
		String hql=" from DetectionLine as dl where 1=1 ";
		Map<String, Object> tMap=new HashMap<String, Object>();
		//动态拼接sql语句
		if(detectionLinePage.getStationName()!=null &&!detectionLinePage.getStationName().trim().equals(""))
		{
			hql+=" and dl.inspectionStation.stationName like :stationName ";
			tMap.put("stationName", "%"+detectionLinePage.getStationName().trim()+"%");
		}
		if(detectionLinePage.getLineName()!=null && !detectionLinePage.getLineName().equals(""))
		{
			hql+=" and dl.lineName like :lineName ";
			tMap.put("lineName", "%"+detectionLinePage.getLineName().trim()+"%");
		}
		if(detectionLinePage.getLineStatus()!=null && detectionLinePage.getLineStatus()!=2)
		{
			hql+=" and dl.lineStatus=:lineStatus ";
			tMap.put("lineStatus", detectionLinePage.getLineStatus());
		}
		dg.setTotal(detectionLineDao.count("select count(*) "+hql,tMap));
		List<DetectionLine> list=detectionLineDao.find(hql,tMap,detectionLinePage.getPage(),detectionLinePage.getRows());
		List<DetectionLinePage> dlplist=new ArrayList<DetectionLinePage>();
		for(DetectionLine dl:list)
		{
			DetectionLinePage dlp=new DetectionLinePage();
			BeanUtils.copyProperties(dl, dlp);
			if(dl.getInspectionStation()!=null)
			{
			   dlp.setStationId(dl.getInspectionStation().getStationId());
			   dlp.setStationName(dl.getInspectionStation().getStationName());
			}
			else {
				dlp.setStationId(0);
				dlp.setStationName("未知检测站");
			}
			dlplist.add(dlp);
		}
		dg.setRows(dlplist);
		return dg;
	}
	/**
	 * 注销检测线，支持批量
	 */
	@Override
	public Json cancelDetectionLine(DetectionLinePage detectionLinePage) {
		Json  res=new Json();
		String flag="";
		if(detectionLinePage.getIds()!=null && !detectionLinePage.getIds().trim().equals(""))
		{
			try{
				 for(String id:detectionLinePage.getIds().trim().split(","))
				 {
					 flag=id;
					 String hql=" from DetectionLine as dl where dl.lineId=:lineId";
					 Map<String, Object> tMap=new HashMap<String, Object>();
					 tMap.put("lineId", Integer.parseInt(id));
					 DetectionLine dLine=detectionLineDao.get(hql,tMap);
					 dLine.setLineStatus(1);
					 detectionLineDao.update(dLine);
				 }
				 res.setSuccess(true);
				 res.setMsg("注销检测线信息成功");
				
			}catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("注销id为"+flag+"的检测线信息失败");
				return res;
			}
		}
		return res;
	}
	/**
	 * 删除检测线信息（用作扩展）
	 */
	@Override
	public Json deleteDetectionLine(DetectionLinePage detectionLinePage) {
		Json  res=new Json();
		String flag="";
		if(detectionLinePage.getIds()!=null && !detectionLinePage.getIds().trim().equals(""))
		{
			try{
				 for(String id:detectionLinePage.getIds().trim().split(","))
				 {
					 flag=id;
					 String hql=" from DetectionLine as dl where dl.lineId=:lineId";
					 Map<String, Object> tMap=new HashMap<String, Object>();
					 tMap.put("lineId", Integer.parseInt(id));
					 DetectionLine dLine=detectionLineDao.get(hql,tMap);
					 detectionLineDao.delete(dLine);
				 }
				 res.setSuccess(true);
				 res.setMsg("删除检测线信息成功");
				
			}catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("删除id为"+flag+"的检测线信息失败");
				return res;
			}
		}
		return res;
	}
    /**
     * 修改检测线的信息
     */
	@Override
	public Json modifyDetectionLine(DetectionLinePage detectionLinePage) {
		Json res=new Json();
		if(detectionLinePage!=null)
		{
			String hql="from DetectionLine as dl where dl.lineId="+detectionLinePage.getLineId();
			DetectionLine dlLine=detectionLineDao.find(hql).get(0);
			if(!dlLine.getLineName().trim().equals(detectionLinePage.getLineName().trim()))
			{
				String check_name_hql="select count(*) from DetectionLine as dl where dl.lineName='"+detectionLinePage.getLineName().trim()+"' and dl.inspectionStation.stationId="+detectionLinePage.getStationId();
				 long name_count=detectionLineDao.count(check_name_hql);
			     if(name_count>0)
			     {
		        	res.setSuccess(false);
		            res.setMsg("检测线名称已经存在，请重新输入");
		            return res;
			      }
			}
			if(dlLine.getLocaleId()!=detectionLinePage.getLocaleId())
			{
				String check_localeId_hql="select count(*) from DetectionLine as dl where dl.localeId="+detectionLinePage.getLineId()+" and dl.inspectionStation.stationId="+detectionLinePage.getStationId();
		        long locale_count=detectionLineDao.count(check_localeId_hql);
		        if(locale_count>0)
		        {
		        	res.setSuccess(false);
		        	res.setMsg("检测线站内编号已经存在，请重新输入");
		        	return res;
		        }
			}
			
	        BeanUtils.copyProperties(detectionLinePage, dlLine);
	        InspectionStation inspectionStation=new InspectionStation();
	        inspectionStation.setStationId(detectionLinePage.getStationId());
	        dlLine.setInspectionStation(inspectionStation);
	        //可能要进行一些赋值的调整
	         try{
	        	 detectionLineDao.update(dlLine);
	        	 res.setSuccess(true);
	        	 res.setMsg("修改检测线信息成功");
	         }catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("修改检测线信息失败");
				return res;
			}
		}
		return res;
	}

	@Override
	public List<DetectionLinePage> getDetectionLineName(DetectionLinePage detectionLinePage) {
		List<DetectionLine> list=new ArrayList<DetectionLine>();
		List<DetectionLinePage> res=new ArrayList<DetectionLinePage>();
		String q="";
		Map<String, Object> tMap=new HashMap<String, Object>();
		String hql="from DetectionLine as dl where dl.lineStatus=0 ";
		if(detectionLinePage!=null )
		{
			if(detectionLinePage.getStationName()!=null && !detectionLinePage.getStationName().trim().equals(""))
			{
			    hql+=" and dl.inspectionStation.stationName like :stationName";
			    tMap.put("stationName", "%"+detectionLinePage.getStationName().trim()+"%");
			}
			if(detectionLinePage.getStationId()!=null)
			{
				hql+=" and dl.inspectionStation.stationId=:stationId ";
				tMap.put("stationId", detectionLinePage.getStationId());
			}
			if(detectionLinePage.getQ()!=null && !detectionLinePage.getQ().trim().equals(""))
		    {
				q="%"+detectionLinePage.getQ().trim()+"%";
				hql+=" and dl.lineName like :q";
				tMap.put("q", q);
			 }
		   list=detectionLineDao.find(hql, tMap,detectionLinePage.getPage(),detectionLinePage.getRows());
		   for(DetectionLine dl:list)
		   {
			  if(detectionLinePage.getIsCarlibrated()!=null &&detectionLinePage.getIsCarlibrated()==1)
			  {
				  Calendar calendar=Calendar.getInstance();
			      int year=calendar.get(calendar.YEAR);
				  int month=calendar.get(calendar.MONTH)+1;
				  int day=calendar.get(calendar.DATE);
				  String time=year+"-"+month+"-"+day+" 00:00:00";
				  System.out.println(time);
				  String hql_count="select count(*) from CalibrationRecord as cr where cr.detectionLine.lineId=:lineId and cr.calibrationTime>=:time ";
				  Map<String,Object> paramMap=new HashMap<String, Object>();
				  paramMap.put("lineId", dl.getLineId());
				  paramMap.put("time",Timestamp.valueOf(time));
				  long count=calibrationDao.count(hql_count,paramMap);
				  if(count>0)
				  {
					   DetectionLinePage dlp=new DetectionLinePage();
					   dlp.setLineId(dl.getLineId());
					   dlp.setLineName(dl.getLineName());
					   res.add(dlp);
				  }
			  }
			  else 
			  {
				   DetectionLinePage dlp=new DetectionLinePage();
				   dlp.setLineId(dl.getLineId());
				   dlp.setLineName(dl.getLineName());
				   res.add(dlp);
			  }
		   }
		}
		return res;
	}

	@Override
	public Json exportDetectionLine(DetectionLinePage detectionLinePage){
		List<Object> pageList = new ArrayList<Object>();
		if (detectionLinePage.getIds() != null) {
			for (String id : detectionLinePage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					DetectionLinePage dlp=new DetectionLinePage();
					DetectionLine dl=detectionLineDao.get(DetectionLine.class,Integer.valueOf(id));
					BeanUtils.copyProperties(dl, dlp);
					if(dl.getInspectionStation()!=null)
					{
					   dlp.setStationId(dl.getInspectionStation().getStationId());
					   dlp.setStationName(dl.getInspectionStation().getStationName());
					}
					else {
						dlp.setStationId(0);
						dlp.setStationName("未知检测站");
					}
					pageList.add(dlp);
				}
			}
		}
		String filePath="";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
					"formatExcel", "formatTitle", DetectionLineServiceImpl.class);
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
		result.add(((DetectionLinePage) obj).getLineId().toString());
		result.add(((DetectionLinePage) obj).getStationId().toString());
		result.add(((DetectionLinePage) obj).getStationName());
		result.add(((DetectionLinePage) obj).getLineName());
		result.add(((DetectionLinePage) obj).getLocaleId().toString());
		result.add(((DetectionLinePage) obj).getMaxDetectionNum().toString());
		if (((DetectionLinePage) obj).getLineStatus()== 0)
			result.add("正常");
		else
			result.add("注销");
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("检测站编号");
		result.add("检测站名称");
		result.add("检测线名称");
		result.add("站内编号");
		result.add("最大检测量");
		result.add("状态");
		return result;
	}

	@Override
	public SysUser getStationManager(DetectionLinePage detectionLinePage) {
		
		String hql = "from SysUser as usr where as.userId = (select SM.sysUser from StationManageMent as SM where SM.)";
		Map<String, Object> tMap=new HashMap<String, Object>();
		tMap.put("stationId", detectionLinePage.getStationId());
		sysUserDao.find(hql, tMap);
		
		
		return null;
	}

}
