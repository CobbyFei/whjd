package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.EnvironmentalLabel;
import com.dbsoft.whjd.model.EnvironmentalLabelCollar;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
@Service("environmentalLabelService")
public class EnvironmentalLabelServiceImpl implements IEnvironmentalLabelService{
	private IBaseDao<EnvironmentalLabel> environmentalLabelDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}

	public IBaseDao<EnvironmentalLabel> getEnvironmentalLabelDao() {
		return environmentalLabelDao;
	}

	@Resource(name = "baseDao")
	public void setEnvironmentalLabelDao(IBaseDao<EnvironmentalLabel> environmentalLabelDao) {
		this.environmentalLabelDao = environmentalLabelDao;
	}
     //拷贝字符串到时间
	public void strToTime(EnvironmentalLabelPage ePage, EnvironmentalLabel eLabel) {
		if (ePage.getVehicleRegisterTime() != null && !ePage.getVehicleRegisterTime().trim().equals("")) {
			eLabel.setVehicleRegisterTime(ChangeTimeFormat.getInstance().strToTimeStamp(ePage.getVehicleRegisterTime().trim()));
		}
		if (ePage.getDistributionCertTime() != null && !ePage.getDistributionCertTime().trim().equals("")) {
			eLabel.setDistributionCertTime(ChangeTimeFormat.getInstance().strToTimeStamp(ePage.getDistributionCertTime().trim()));
		}
		if (ePage.getIssueDate() != null && !ePage.getIssueDate().trim().equals("")) {
			eLabel.setIssueDate(ChangeTimeFormat.getInstance().strToTimeStamp(ePage.getIssueDate().trim()));
		}
		if (ePage.getValidPeriod() != null && !ePage.getValidPeriod().trim().equals("")) {
			eLabel.setValidPeriod(ChangeTimeFormat.getInstance().strToTimeStamp(ePage.getValidPeriod().trim()));
		}
	}
     //拷贝时间到字符串	
	public void timeToStr(EnvironmentalLabel eLabel,EnvironmentalLabelPage ePage) {
		if (eLabel.getVehicleRegisterTime()!=null) {
			ePage.setVehicleRegisterTime(ChangeTimeFormat.getInstance().timeStampToString(eLabel.getVehicleRegisterTime()));
		}
		if (eLabel.getDistributionCertTime()!=null) {
			ePage.setDistributionCertTime(ChangeTimeFormat.getInstance().timeStampToString(eLabel.getDistributionCertTime()));
		}
		if (eLabel.getIssueDate()!=null) {
			ePage.setIssueDate(ChangeTimeFormat.getInstance().timeStampToString(eLabel.getIssueDate()));
		}
		if (eLabel.getValidPeriod()!=null) {
			ePage.setValidPeriod(ChangeTimeFormat.getInstance().timeStampToString(eLabel.getValidPeriod()));
		}
	}
	//状态位拷贝
	public void statusToStr(EnvironmentalLabel eLabel,EnvironmentalLabelPage ePage) {
		if (eLabel.getIsCancel()!=null) {
			if (eLabel.getIsCancel()==true) {
				ePage.setIsCancel("注销");
			}else if(eLabel.getIsCancel()==false){
				ePage.setIsCancel("正常");
			}else {
				ePage.setIsCancel("未设置状态位");
			}
		}
		if (eLabel.getIsPrint()!=null) {
			if (eLabel.getIsPrint()==true) {
				ePage.setIsPrint("已打印");
			}else if (eLabel.getIsPrint()==false) {
				ePage.setIsPrint("未打印");
			}else {
				ePage.setIsPrint("未设置状态位");
			}
		}
	}
	
	public void strToStatus(EnvironmentalLabelPage ePage,EnvironmentalLabel eLabel) {
		if (ePage.getIsCancel()!=null&&!ePage.getIsCancel().trim().equals("")) {
			if (ePage.getIsCancel().trim().equals("注销")) {
				eLabel.setIsCancel(true);
			}else {
				eLabel.setIsCancel(false);
			}
		}
		if (ePage.getIsPrint()!=null&&!ePage.getIsPrint().trim().equals("")) {
			if (ePage.getIsPrint().trim().equals("已打印")) {
				eLabel.setIsPrint(true);
			}else  {
				eLabel.setIsPrint(false);
			}
		}
	}
	@Override
	public DataGrid getEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage) {
		DataGrid dg = new DataGrid();
		Map<String, Object> tMap = new HashMap<String, Object>();
		//获取session
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession=req.getSession();
		String userStationName=(String) hSession.getAttribute("stationName");
		System.out.println(userStationName);
		String hql = "FROM EnvironmentalLabel as el where 1=1";
		List<EnvironmentalLabelPage> ePages=new ArrayList<EnvironmentalLabelPage>();
		if (!userStationName.trim().equals("市局")&&!userStationName.trim().equals("")) {
			tMap.put("stationName", userStationName.trim());
			hql+=" and el.inspectionStation.stationName=:stationName";
		}
		if (environmentalLabelPage.getInspectionStationId()!=null&&userStationName.trim().equals("市局")) {
			tMap.put("stationId", environmentalLabelPage.getInspectionStationId());
			hql+=" and el.inspectionStation.stationId=:stationId";
		}
		if (environmentalLabelPage.getLabelId()!=null&&!environmentalLabelPage.getLabelId().trim().equals("")) {
			tMap.put("labelId","%"+environmentalLabelPage.getLabelId().trim()+"%");
			hql+=" and el.labelId like:labelId";
		}
		if (environmentalLabelPage.getLabelType()!=null&&!environmentalLabelPage.getLabelType().trim().equals("")) {
			if (environmentalLabelPage.getLabelType().trim().equals("绿标")||environmentalLabelPage.getLabelType().equals("黄标")) {
				tMap.put("labelType","%"+environmentalLabelPage.getLabelType().trim()+"%");
				hql+=" and el.labelType like:labelType";
			}
		}
		if (environmentalLabelPage.getLicence()!=null&&!environmentalLabelPage.getLicence().trim().equals("")) {
			tMap.put("licence","%"+environmentalLabelPage.getLicence().trim()+"%");
			hql+=" and el.licence like:licence";
		}
		if (environmentalLabelPage.getVehicleOwner()!=null&&!environmentalLabelPage.getVehicleOwner().trim().equals("")) {
			tMap.put("vehicleOwner","%"+environmentalLabelPage.getVehicleOwner().trim()+"%");
			hql+=" and el.vehicleOwner like:vehicleOwner";
		}
		if (environmentalLabelPage.getYear()!=null) {
			tMap.put("year",environmentalLabelPage.getYear());
			hql+=" and el.year=:year";
		}
		if (environmentalLabelPage.getBeforeIssueDate()!=null&&!environmentalLabelPage.getBeforeIssueDate().trim().equals("")) {
			tMap.put("beforeIssueDate",ChangeTimeFormat.getInstance().strToTimeStamp(environmentalLabelPage.getBeforeIssueDate().trim()));
			hql+=" and el.distributionCertTime>=:beforeIssueDate";
		}
		if (environmentalLabelPage.getAfterIssueDate()!=null&&!environmentalLabelPage.getAfterIssueDate().trim().equals("")) {
			tMap.put("afterIssueDate",ChangeTimeFormat.getInstance().strToTimeStamp(environmentalLabelPage.getAfterIssueDate().trim()));
			hql+=" and el.distributionCertTime<=:afterIssueDate";
		}
		if (environmentalLabelPage.getIsCancel()!=null&&!environmentalLabelPage.getIsCancel().trim().equals("")) {
			if (environmentalLabelPage.getIsCancel().trim().equals("注销")) {
				tMap.put("isCancel",true);
				hql+=" and el.isCancel=:isCancel";
			}
			if (environmentalLabelPage.getIsCancel().trim().equals("正常")) {
				tMap.put("isCancel",false);
				hql+=" and el.isCancel=:isCancel";
			}
		}else {
			tMap.put("isCancel",false);
			hql+=" and el.isCancel=:isCancel";
		}
		if (environmentalLabelPage.getIsPrint()!=null&&!environmentalLabelPage.getIsPrint().trim().equals("")) {
			if (environmentalLabelPage.getIsPrint().trim().equals("已打印")) {
				tMap.put("isPrint",true);
				hql+=" and el.isPrint=:isPrint";
			}
			if (environmentalLabelPage.getIsPrint().trim().equals("未打印")) {
				tMap.put("isPrint",false);
				hql+=" and el.isPrint=:isPrint";
			}

		}
		dg.setTotal(environmentalLabelDao.count("SELECT COUNT(*) " + hql, tMap));
		hql+=" order by el.id desc";
		List<EnvironmentalLabel> eLabels = environmentalLabelDao.find(hql,tMap,environmentalLabelPage.getPage(),environmentalLabelPage.getRows());
		for (int i = 0; i < eLabels.size(); i++) {
			EnvironmentalLabel eLabel=eLabels.get(i);
			EnvironmentalLabelPage ePage=new EnvironmentalLabelPage();
			BeanUtils.copyProperties(eLabel, ePage,new String[]{"vehicleRegisterTime","distributionCertTime","issueDate","validPeriod","isPrint","isCancel"});
		    statusToStr(eLabel, ePage);
		    timeToStr(eLabel, ePage);
		    if (eLabel.getInspectionStation()!=null) {
				ePage.setStationName(eLabel.getInspectionStation().getStationName());
			}
		    ePages.add(ePage);
		}

		dg.setRows(ePages);
		return dg;
	}

	@Override
	public Integer addEnvironmentalLabel(DetectionCommisionSheet sheet) {
		
		/*String labelInfo = null;
        String[] eLabel=labelInfo.split(",");
		String inspectionStationName=eLabel[0];//检测站编号
		String labelId=eLabel[1];//标志号
		String licence=eLabel[2];//车牌号
		String vehicleModelCode=eLabel[3];//车型
		String vehicleBrand=eLabel[4];//车辆品牌
		String fuelType=eLabel[5];//燃油类型
		String vehicleClassification=eLabel[6];//车辆类型
		String vehicleOwner=eLabel[7];//车主
		String vehicleRegisterTime=eLabel[8];//注册时间
		String distributionCertTime=eLabel[9];//发证时间
		String vinNo=eLabel[10];//车架号
		String emissionStandard=eLabel[11];//排放标准
		String issueDate=eLabel[12];//核发日期
		String validPeriod=eLabel[13];//有效期
		String labelType=eLabel[14];//标志类型
		Integer year=Integer.parseInt(eLabel[15]);//标志年度号
		*/		
		String inspectionStationName = sheet.getStationName();
		String labelId = null;
		String licence = sheet.getLicence();
		String vehicleModelCode = sheet.getVehicleModelCode();
		String vehicleBrand = null;
		String fuelType = sheet.getFuelType();
		String vehicleClassification = sheet.getVehicleClass();
		String vehicleOwner = sheet.getVehicleOwnerName();
		String vehicleRegisterTime = ChangeTimeFormat.getInstance().timeStampToString(sheet.getVehicleRegisterDate());
		String distributionCertTime = null;
		String vinNo = sheet.getVin();
		String emissionStandard = sheet.getEmissionStandard()+"";
		String issueDate = ChangeTimeFormat.getInstance().timeStampToString(sheet.getVechileIssueCertTime());
		String validPeriod = ChangeTimeFormat.getInstance().timeStampToString(sheet.getValidatePeriod());
		String labelType = null;
		Integer year = Integer.parseInt(issueDate.subSequence(2, 4).toString());
		Integer stationId = 1;
		Integer userId = 1;
		String licenseColor = sheet.getLicenseColor();
		
		
		if(sheet.getFreeAccelerationMethod()!=null)
			userId = sheet.getFreeAccelerationMethod().getSysUserByInspectorId().getUserId();
		else if(sheet.getLugDownMethod()!=null)
			userId = sheet.getLugDownMethod().getSysUserByInspectorId().getUserId();
		else if(sheet.getMotorTwoSpeedIdleMethod()!=null)
			userId = sheet.getMotorTwoSpeedIdleMethod().getSysUser().getUserId();
		else if(sheet.getSteadyStateMethod()!=null)
			userId = sheet.getSteadyStateMethod().getSysUserByInspecOperatorId().getUserId();
		else if(sheet.getTwoSpeedIdleMethod()!=null)
			userId = sheet.getTwoSpeedIdleMethod().getSysUserByInspecOperatorId().getUserId();
		
		
		Map<String, Object> tMap = new HashMap<String, Object>();
		EnvironmentalLabel environmentalLabel=new EnvironmentalLabel();
		if (inspectionStationName!=null&&!inspectionStationName.trim().equals("")) {
			tMap.put("stationName", inspectionStationName);
			String hql="FROM InspectionStation as r where r.stationName =:stationName ";
			InspectionStation iStation = inspectionStationDao.get(hql, tMap);
			stationId = iStation.getStationId();
			environmentalLabel.setInspectionStation(iStation);
		}
		
		String stationIds = String.format("%03d", stationId);
		String userIds = String.format("%03d", userId);
		
		String shql="FROM EnvironmentalLabel as e where e.labelId = "
				+ "(select max(labelId) from EnvironmentalLabel as en where en.labelId like '3402"+year+stationIds+userIds+"%') ";
		EnvironmentalLabel el = environmentalLabelDao.get(shql);
		if(el!=null){
			String maxLabelId = el.getLabelId();
			Integer maxLastNum = Integer.parseInt(maxLabelId.substring(maxLabelId.length()-3, maxLabelId.length()));
			Integer newNum = maxLastNum+1;
			String newNums = String.format("%03d", newNum);
			labelId = "3402"+year+stationIds+userIds+newNums;
		}else 
			labelId = "3402"+year+stationIds+userIds+"001";
		//TODO
		if (labelId!=null&&!labelId.trim().equals("")) {
			environmentalLabel.setLabelId(labelId);
		}		
		if (licence!=null&&!licence.trim().equals("")) {
			environmentalLabel.setLicence(licence);
		}
		if (vehicleBrand!=null&&!vehicleBrand.trim().equals("")) {
			environmentalLabel.setVehicleBrand(vehicleBrand);
		}
		if (vehicleModelCode!=null&&!vehicleModelCode.trim().equals("")) {
			environmentalLabel.setVehicleModelCode(vehicleModelCode);
		}
		if (fuelType!=null&&!fuelType.trim().equals("")) {
			environmentalLabel.setFuelType(fuelType);
		}
		if (vehicleClassification!=null&&!vehicleClassification.trim().equals("")) {
			environmentalLabel.setVehicleClassification(vehicleClassification);
		}
		if (vehicleOwner!=null&&!vehicleOwner.trim().equals("")) {
			environmentalLabel.setVehicleOwner(vehicleOwner);
		}
		if (vehicleRegisterTime!=null&&!vehicleRegisterTime.trim().equals("")) {
			environmentalLabel.setVehicleRegisterTime(ChangeTimeFormat.getInstance().strToTimeStamp(vehicleRegisterTime));
		}
		if (issueDate!=null&&!issueDate.trim().equals("")) {
			environmentalLabel.setIssueDate(ChangeTimeFormat.getInstance().strToTimeStamp(issueDate));
		}
		if (distributionCertTime!=null&&!distributionCertTime.trim().equals("")) {
			environmentalLabel.setDistributionCertTime(ChangeTimeFormat.getInstance().strToTimeStamp(distributionCertTime));
		}
		if (vinNo!=null&&!vinNo.trim().equals("")) {
			environmentalLabel.setVinNo(vinNo);
		}
		if (emissionStandard!=null&&!emissionStandard.trim().equals("")) {
			environmentalLabel.setEmissionStandard(emissionStandard);
		}
		if (validPeriod!=null&&!validPeriod.trim().equals("")) {
			environmentalLabel.setValidPeriod(ChangeTimeFormat.getInstance().strToTimeStamp(validPeriod));
		}
		if (labelType!=null&&!labelType.trim().equals("")) {
			environmentalLabel.setLabelType(labelType);
		}
		if (licenseColor!=null&&!licenseColor.trim().equals("")) {
			environmentalLabel.setLicenseColor(licenseColor);
		}
		if (year!=null) {
			environmentalLabel.setYear(year);
		}
         environmentalLabel.setIsCancel(false);
         environmentalLabel.setIsPrint(false);
		try {
			environmentalLabelDao.save(environmentalLabel);
			return 0;//正常添加返回0，否则返回1
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}

	@Override
	public Json updateEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		//System.out.println(environmentalLabelCollarPage.getCollarTime()+environmentalLabelCollarPage.getId());
		try {
			tMap.put("id", environmentalLabelPage.getId());
			String hql="FROM EnvironmentalLabel as r where r.id=:id ";
			EnvironmentalLabel environmentalLabel = environmentalLabelDao.get(hql, tMap);
			if (environmentalLabel.getId()!=null) {
				System.out.println("sdfsdfasfsafsdfsd45");
			}
			
			//BeanUtils.copyProperties(environmentalLabelPage, environmentalLabel,new String[] {"vehicleRegisterTime","distributionCertTime","issueDate","validPeriod","isPrint","isCancel"});
	        //strToTime(environmentalLabelPage, environmentalLabel);
            strToStatus(environmentalLabelPage, environmentalLabel);
			environmentalLabelDao.update(environmentalLabel);
			json.setMsg("更新环保标志发放记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		    json.setMsg("更新环保标志发放记录失败！");
		}
		return json;
	}

	@Override
	public Json cancelEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage) {
	    Json json=new Json();
	    String flag="";
		if(environmentalLabelPage.getIds()!=null)
		{
			try {
				if(!environmentalLabelPage.getIds().trim().equals(""))
				{
					for(String id:environmentalLabelPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("id", Integer.parseInt(id));
							//tMap.put("status",1);
					        String hql="FROM EnvironmentalLabel as elc where elc.id=:id " ;
							EnvironmentalLabel ls=environmentalLabelDao.get(hql, tMap);
                            ls.setIsCancel(true);
                            environmentalLabelDao.update(ls);
						}
					}
					json.setSuccess(true);
					json.setMsg("注销环保标志发放记录成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("删除环保标志id为"+flag+"的发放记录失败");
				return json;
			}
		}
		return json;
	}
	//通过id查找标志
	@Override
	public EnvironmentalLabelPage getEnvironmentalLabelById(Integer environmentalLabelId) {
		EnvironmentalLabelPage ePage=new EnvironmentalLabelPage();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("id", environmentalLabelId);
		String hql = "FROM EnvironmentalLabel as el where el.id=:id";
		EnvironmentalLabel eLabel = environmentalLabelDao.get(hql,tMap);
		if (eLabel!=null) {
			BeanUtils.copyProperties(eLabel, ePage,new String[]{"vehicleRegisterTime","distributionCertTime","issueDate","validPeriod","isPrint","isCancel"});
		    statusToStr(eLabel, ePage);
		    timeToStr(eLabel, ePage);
		    if (eLabel.getInspectionStation()!=null) {
				ePage.setStationName(eLabel.getInspectionStation().getStationName());
			}
		    
		}
		return ePage;
	}
	@Override
	public Json setIsPrint(Integer environmentalLabelId) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("id", environmentalLabelId);
		String hql = "FROM EnvironmentalLabel as el where el.id=:id";
		EnvironmentalLabel eLabel = environmentalLabelDao.get(hql,tMap);
		if (eLabel != null) {
			eLabel.setIsPrint(true);
			json.setSuccess(true);
			json.setMsg("打印成功");
		} else {
			json.setSuccess(false);
			json.setMsg("打印失败");
		}
		return json;
	}

}
