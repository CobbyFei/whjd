package com.dbsoft.whjd.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DistributionTask;
import com.dbsoft.whjd.model.EnvironmentalLabelCollar;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDistributionTaskService;

@Service("distributionTaskService")
public class DistributionTaskServiceImpl implements IDistributionTaskService{
	private IBaseDao<DetectionCommisionSheet>  detectionCommisionSheetDao;
    private IBaseDao<DistributionTask> distributionTaskdDao;
	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}
	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}
	public IBaseDao<DistributionTask> getDistributionTaskdDao() {
		return distributionTaskdDao;
	}
	@Resource(name = "baseDao")
	public void setDistributionTaskdDao(IBaseDao<DistributionTask> distributionTaskdDao) {
		this.distributionTaskdDao = distributionTaskdDao;
	}
	@Override
	public Json addDistributionTask(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json json = new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		String hql = "FROM DetectionCommisionSheet as dSheet where dSheet.id=:id";
		try {
			tMap.put("id", detectionCommisionSheetPage.getId());
			DetectionCommisionSheet dSheet = detectionCommisionSheetDao.get(hql, tMap);
			DistributionTask task = new DistributionTask();
			task.setCommisionSheetId(detectionCommisionSheetPage.getId());
			task.setVin(detectionCommisionSheetPage.getVin().trim());
			task.setEngineCode(detectionCommisionSheetPage.getEngineCode().trim());
			task.setEngineModel(detectionCommisionSheetPage.getEngineModel().trim());
			task.setIssueDate(detectionCommisionSheetPage.getVechileIssueCertTime().trim());
			task.setValidatePeriod(detectionCommisionSheetPage.getValidatePeriod().trim());
			task.setVehicleRegisterDate(detectionCommisionSheetPage.getVehicleRegisterDate().trim());
			task.setStationName(detectionCommisionSheetPage.getStationName().trim());
			task.setLicence(detectionCommisionSheetPage.getLicence().trim());
			task.setTotalQuality(detectionCommisionSheetPage.getMaxTotalQuality());
			task.setRemarks("无");
			task.setVehicleOwnerName(detectionCommisionSheetPage.getVehicleOwnerName().trim());
			task.setVehicleAddress(detectionCommisionSheetPage.getVehicleOwnerAddress().trim());
			task.setVechileModel(detectionCommisionSheetPage.getVehicleModelCode().trim());
			if (detectionCommisionSheetPage.getVehicleType() != null) {
				if (detectionCommisionSheetPage.getVehicleType() == 0) {
					task.setVehicleType("新车");
					task.setLabelDistributeType(0);
				} else if (detectionCommisionSheetPage.getVehicleType() == 1) {
					task.setVehicleType("外地转入车");
				} else {
					task.setVehicleType("在用车");
					task.setLabelDistributeType(1);
				}
			}
			task.setVehicleLoadNum(detectionCommisionSheetPage.getVehicleLoadNum());
			task.setLicenseColor(detectionCommisionSheetPage.getLicenseColor().trim());
			distributionTaskdDao.save(task);
			dSheet.setRequestStatus(1);
			dSheet.setReceiveStatus(1);
			detectionCommisionSheetDao.update(dSheet);
			json.setMsg("添加发标任务成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("添加发标任务失败！");
			json.setSuccess(false);
		}

		return json;
	}
	@Override
	public Json reDistributionTask(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		try {
			tMap.put("id", detectionCommisionSheetPage.getId());
			String hql="FROM DetectionCommisionSheet as r where r.id=:id ";
			//EnvironmentalLabelCollar environmentalLabelCollar = environmentalLabelCollarDao.get(hql, tMap);
			//BeanUtils.copyProperties(environmentalLabelCollarPage, environmentalLabelCollar,new String[] {"collarTime","status"});
            DetectionCommisionSheet dSheet=detectionCommisionSheetDao.get(hql,tMap);

            String taskHql="FROM DistributionTask as r where r.commisionSheetId=:id ";
            DistributionTask dTask=distributionTaskdDao.get(taskHql, tMap);
            if (dTask!=null) {
				dTask.setHandleStatus(0);
			}
            distributionTaskdDao.update(dTask);
            if (dSheet!=null) {
				dSheet.setReceiveStatus(1);
				dSheet.setRequestStatus(1);
			}
            detectionCommisionSheetDao.update(dSheet);
			json.setMsg("重新申请发标成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		    json.setMsg("重新申请发标失败！");
		}
		return json;
	}

}
