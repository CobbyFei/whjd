package com.dbsoft.whjd.service.impl;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.FreeAccelerationMethod;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.MotorTwoSpeedIdleMethod;
import com.dbsoft.whjd.model.SteadyStateMethod;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.model.WebServiceDataInteraction;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.WebServiceDataInteractionPage;
import com.dbsoft.whjd.service.IWebServiceDataService;
@Service("webServiceDataService")
public class WebServiceDataServiceImpl implements IWebServiceDataService{
    private IBaseDao<WebServiceDataInteraction> webServiceDataInteractionDao;
    private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
    private IBaseDao<LugDownMethod> lugDownMethodDao;
    private IBaseDao<SteadyStateMethod> steayStateMehodDao;
    private IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao;
    private IBaseDao<FreeAccelerationMethod> freeAcclerationMethodDao;
    private IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleDao;
	@Override
	public Json addWebServiceData(
			Integer id ) {
		Json res=new Json();
		if(id!=null)
		{
			DetectionCommisionSheet detectionCommisionSheet=detectionCommisionSheetDao.get(DetectionCommisionSheet.class,id);
			if(detectionCommisionSheet==null)
			{
				res.setSuccess(false);
				res.setMsg("该委托单不存在");
			}
			else {
				WebServiceDataInteraction webServiceDataInteraction=new WebServiceDataInteraction();
				Integer cs_id=detectionCommisionSheet.getId();
				String methodName=detectionCommisionSheet.getDetectionMethod();
				String licence=detectionCommisionSheet.getLicence();
				Integer lineLocalID=0;
				Integer stationId=0;
				Integer reportID=0;
				if(detectionCommisionSheet.getFreeAccelerationMethod()!=null)
				{
					lineLocalID=detectionCommisionSheet.getFreeAccelerationMethod().getDetectionLine().getLocaleId();
				    reportID=detectionCommisionSheet.getFreeAccelerationMethod().getId();
				    detectionCommisionSheet.getFreeAccelerationMethod().setReportStatus(1);
				    freeAcclerationMethodDao.update(detectionCommisionSheet.getFreeAccelerationMethod());
				    detectionCommisionSheet.setCommisionSheetStatus(1);
				    detectionCommisionSheetDao.update(detectionCommisionSheet);
				}
				if(detectionCommisionSheet.getLugDownMethod()!=null)
				{
					lineLocalID=detectionCommisionSheet.getLugDownMethod().getDetectionLine().getLocaleId();
				    reportID=detectionCommisionSheet.getLugDownMethod().getId();
				    detectionCommisionSheet.getLugDownMethod().setReportStatus(1);
				    lugDownMethodDao.update(detectionCommisionSheet.getLugDownMethod());
				    detectionCommisionSheet.setCommisionSheetStatus(1);
				    detectionCommisionSheetDao.update(detectionCommisionSheet);
				}
				if(detectionCommisionSheet.getMotorTwoSpeedIdleMethod()!=null)
				{
					lineLocalID=detectionCommisionSheet.getMotorTwoSpeedIdleMethod().getDetectionLine().getLocaleId();
				    reportID=detectionCommisionSheet.getMotorTwoSpeedIdleMethod().getId();
				    detectionCommisionSheet.getMotorTwoSpeedIdleMethod().setReportStatus(1);
				    motorTwoSpeedIdleDao.update(detectionCommisionSheet.getMotorTwoSpeedIdleMethod());
				    detectionCommisionSheet.setCommisionSheetStatus(1);
				    detectionCommisionSheetDao.update(detectionCommisionSheet);
				}
				if(detectionCommisionSheet.getSteadyStateMethod()!=null)
				{
					lineLocalID=detectionCommisionSheet.getSteadyStateMethod().getDetectionLine().getLocaleId();
				    reportID=detectionCommisionSheet.getSteadyStateMethod().getId();
				    detectionCommisionSheet.getSteadyStateMethod().setReportStatus(1);
				    steayStateMehodDao.update(detectionCommisionSheet.getSteadyStateMethod());
				    detectionCommisionSheet.setCommisionSheetStatus(1);
				    detectionCommisionSheetDao.update(detectionCommisionSheet);
				}
				if(detectionCommisionSheet.getTwoSpeedIdleMethod()!=null)
				{
					  lineLocalID=detectionCommisionSheet.getTwoSpeedIdleMethod().getDetectionLine().getLocaleId();
				      reportID=detectionCommisionSheet.getTwoSpeedIdleMethod().getId();
				      detectionCommisionSheet.getTwoSpeedIdleMethod().setReportStatus(1);
				      twoSpeedIdleMethodDao.update(detectionCommisionSheet.getTwoSpeedIdleMethod());
				      detectionCommisionSheet.setCommisionSheetStatus(1);
					  detectionCommisionSheetDao.update(detectionCommisionSheet);
				}
				HttpSession session=ServletActionContext.getRequest().getSession();
				String stationIdStr=session.getAttribute("stationId").toString().trim();
				if(stationIdStr!=null && !stationIdStr.equals(""))
				{
					stationId=Integer.parseInt(stationIdStr);
				}
				webServiceDataInteraction.setCsId(cs_id);
				webServiceDataInteraction.setReportId(reportID);
				webServiceDataInteraction.setLicence(licence);
				webServiceDataInteraction.setMethodName(methodName);
				webServiceDataInteraction.setStatus(0);
				webServiceDataInteraction.setStationId(stationId);
				webServiceDataInteraction.setLineLocalId(lineLocalID);
				
				webServiceDataInteractionDao.save(webServiceDataInteraction);
				res.setSuccess(true);
				res.setMsg("添加检测任务成功");
				
			}
		}
		else {
			res.setMsg("委托单编号未传入");
			res.setSuccess(false);
		}
		return res;
	}
	
	
	public IBaseDao<WebServiceDataInteraction> getWebServiceDataInteractionDao() {
		return webServiceDataInteractionDao;
	}
	
	@Resource(name="baseDao")
	public void setWebServiceDataInteractionDao(
			IBaseDao<WebServiceDataInteraction> webServiceDataInteractionDao) {
		this.webServiceDataInteractionDao = webServiceDataInteractionDao;
	}
	
	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}
	@Resource(name="baseDao")
	public void setDetectionCommisionSheetDao(
			IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}


	public IBaseDao<LugDownMethod> getLugDownMethodDao() {
		return lugDownMethodDao;
	}

	@Resource(name="baseDao")
	public void setLugDownMethodDao(IBaseDao<LugDownMethod> lugDownMethodDao) {
		this.lugDownMethodDao = lugDownMethodDao;
	}


	public IBaseDao<SteadyStateMethod> getSteayStateMehodDao() {
		return steayStateMehodDao;
	}

	@Resource(name="baseDao")
	public void setSteayStateMehodDao(IBaseDao<SteadyStateMethod> steayStateMehodDao) {
		this.steayStateMehodDao = steayStateMehodDao;
	}


	public IBaseDao<TwoSpeedIdleMethod> getTwoSpeedIdleMethodDao() {
		return twoSpeedIdleMethodDao;
	}

	@Resource(name="baseDao")
	public void setTwoSpeedIdleMethodDao(
			IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao) {
		this.twoSpeedIdleMethodDao = twoSpeedIdleMethodDao;
	}


	public IBaseDao<FreeAccelerationMethod> getFreeAcclerationMethodDao() {
		return freeAcclerationMethodDao;
	}

	@Resource(name="baseDao")
	public void setFreeAcclerationMethodDao(
			IBaseDao<FreeAccelerationMethod> freeAcclerationMethodDao) {
		this.freeAcclerationMethodDao = freeAcclerationMethodDao;
	}


	public IBaseDao<MotorTwoSpeedIdleMethod> getMotorTwoSpeedIdleDao() {
		return motorTwoSpeedIdleDao;
	}

	@Resource(name="baseDao")
	public void setMotorTwoSpeedIdleDao(
			IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleDao) {
		this.motorTwoSpeedIdleDao = motorTwoSpeedIdleDao;
	}
	
	
	

}
