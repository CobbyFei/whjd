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
import com.dbsoft.whjd.model.DetectionMethodReference;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.dbsoft.whjd.util.ChangeTimeFormat;

@Service(value = "steadyStateLimitValueReferenceService")
public class SteadyStateLimitValueReferenceServiceImpl implements
		ILimitValueReferenceService {
	private IBaseDao<LimitValueReference> referenceDao;
	private IBaseDao<DetectionMethodReference> methodDao;
	
	public IBaseDao<LimitValueReference> getReferenceDao() {
		return referenceDao;
	}

	@Resource(name = "baseDao")
	public void setReferenceDao(IBaseDao<LimitValueReference> referenceDao) {
		this.referenceDao = referenceDao;
	}

	public IBaseDao<DetectionMethodReference> getMethodDao() {
		return methodDao;
	}

	@Resource(name = "baseDao")
	public void setMethodDao(IBaseDao<DetectionMethodReference> methodDao) {
		this.methodDao = methodDao;
	}
	
	
	/**
	 * 将LimitValueReference对象复制到LimitValueReferencePage对象中
	 * 
	 * @author gsw
	 */
	public void copyToPage(LimitValueReference reference,
			LimitValueReferencePage page) {
		BeanUtils.copyProperties(reference, page);
		page.setDetectionMedhodName(reference.getDetectionMethodReference()
				.getDetectionMedhodName());
		page.setDetectionMethodId(reference.getDetectionMethodReference()
				.getId());
	}
	
	/**
	 * 将LimitValueReferencePage对象复制到LimitValueReference对象中
	 * 
	 * @author gsw
	 */
	public Json copyToReference(LimitValueReferencePage page,
			LimitValueReference reference) {
		Json j = new Json();
		BeanUtils.copyProperties(page, reference);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "稳态工况法");
		String hql = "FROM DetectionMethodReference as d WHERE d.detectionMedhodName= :methodName";
		DetectionMethodReference method = methodDao.get(hql, params);
		
		if (method != null) {
			reference.setDetectionMethodReference(method);
			j.setSuccess(true);
		}else {
			
			j.setSuccess(false);
			j.setMsg("稳态工况法参照信息尚未设置！");
		}
		return j;
	}
	
	/**
	 * 判断生产时间的起止范围是否合理
	 * 
	 * @author gsw
	 */
	public Json checkRegisterTime(Timestamp minRegisterTime,
			Timestamp maxRegisterTime) {
		Json j = new Json();
		if (maxRegisterTime != null && minRegisterTime != null) {
			if (maxRegisterTime.before(minRegisterTime)
					|| maxRegisterTime.equals(minRegisterTime)) {
				j.setSuccess(false);
				j.setMsg("生产日期的起止范围填写有误，请重新填写！");
				return j;
			}
		} else {
			j.setSuccess(false);
			j.setMsg("生产日期的起止范围未填写完全！");
			return j;
		}
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 判断基准质量的起止范围是否合理
	 * 
	 * @author gsw
	 */
	public Json checkBaseQuality(Double minBaseQuality, Double maxBaseQuality) {
		Json j = new Json();
		if (maxBaseQuality != null && minBaseQuality != null) {
			if (maxBaseQuality <= minBaseQuality) {
				j.setSuccess(false);
				j.setMsg("基准质量的起止范围填写有误，请重新填写！");
				return j;
			}
		} else {
			j.setSuccess(false);
			j.setMsg("基准质量的起止范围未填写完全！");
			return j;
		}
		j.setSuccess(true);
		return j;
	}
	
	
	/**
	 * 添加限值时，需要对已有限值的作用时间进行修正，保证时间上能衔接
	 * 
	 * @author gsw
	 */
	public void checkTimeBound(LimitValueReference reference) {
		List<LimitValueReference> beforeReferences; // 起始时间之前所用限值参照
		List<LimitValueReference> afterReferences; // 结束时间之后所用限制参照
		
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName AND l.vehicleDetailType= :vehicleDetailType";
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("methodName", "稳态工况法");
		param1.put("vehicleDetailType", reference.getVehicleDetailType());
		param1.put("minRegisterTime", reference.getMinRegisterTime());
		String hql1 = hql
				+ " AND l.minRegisterTime< :minRegisterTime AND l.maxRegisterTime> :minRegisterTime";
		beforeReferences = referenceDao.find(hql1, param1);
		
		if (beforeReferences != null && beforeReferences.size() > 0) {
			for (LimitValueReference beforeReference : beforeReferences) {
				if (reference.getId() == null
						|| beforeReference.getId().intValue() != reference.getId()
								.intValue()) {
					beforeReference.setMaxRegisterTime(reference
							.getMinRegisterTime());
					referenceDao.update(beforeReference);
				}
			}
			
		}
		
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("methodName", "稳态工况法");
		param2.put("vehicleDetailType", reference.getVehicleDetailType());
		param2.put("maxRegisterTime", reference.getMaxRegisterTime());
		String hql2 = hql
				+ " AND l.minRegisterTime< :maxRegisterTime AND l.maxRegisterTime> :maxRegisterTime";
		afterReferences = referenceDao.find(hql2, param2);
			
		if (afterReferences != null && afterReferences.size() > 0) {
			for (LimitValueReference afterReference: afterReferences) {
				if (reference.getId() == null
						|| afterReference.getId().intValue() != reference.getId()
								.intValue()) {
					afterReference.setMinRegisterTime(reference
							.getMaxRegisterTime());
					referenceDao.update(afterReference);
				}
			}
		}
	}
	
	
	/**
	 * 添加限值时，需要对已有限值的作用时间进行修正，保证时间上能衔接
	 * 
	 * @author gsw
	 */
	
	public void checkQualityBound(LimitValueReference reference) {
		LimitValueReference beforeReference; // 起始时间之前所用限值参照
		LimitValueReference afterReference; // 结束时间之后所用限制参照
		
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName "
					+" AND l.vehicleDetailType= :vehicleDetailType AND l.minRegisterTime = :minRegisterTime AND l.maxRegisterTime= :maxRegisterTime ";
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("methodName", "稳态工况法");
		param1.put("vehicleDetailType", reference.getVehicleDetailType());
		param1.put("minRegisterTime", reference.getMinRegisterTime());
		param1.put("maxRegisterTime", reference.getMaxRegisterTime());
		
		String hql1 = hql
				+ " AND l.minBaseQuality< :minBaseQuality AND l.maxBaseQuality> :minBaseQuality ";
		param1.put("minBaseQuality", reference.getMinBaseQuality());
		beforeReference = referenceDao.get(hql1, param1);
		
		if (beforeReference != null) {

			if (reference.getId() == null
					|| beforeReference.getId().intValue() != reference.getId()
							.intValue()) {
				beforeReference.setMaxBaseQuality(reference
						.getMinBaseQuality());
				referenceDao.update(beforeReference);
			}
		
		}
		
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("methodName", "稳态工况法");
		param2.put("vehicleDetailType", reference.getVehicleDetailType());
		param2.put("minRegisterTime", reference.getMinRegisterTime());
		param2.put("maxRegisterTime", reference.getMaxRegisterTime());
		
		String hql2 = hql
				+ " AND l.minBaseQuality< :maxBaseQuality AND l.maxBaseQuality> :maxBaseQuality ";
		param2.put("maxBaseQuality", reference.getMaxBaseQuality());
		afterReference = referenceDao.get(hql2, param2);
		
		if (afterReference != null ) {			
				if (reference.getId() == null
						|| afterReference.getId().intValue() != reference.getId()
								.intValue()) {
					afterReference.setMinBaseQuality(reference
							.getMaxBaseQuality());
					referenceDao.update(afterReference);
				}
		}
	}

	
	

	@Override
	public DataGrid getLimitValues(LimitValueReferencePage referencePage) {
		DataGrid dg = new DataGrid();
		List<LimitValueReferencePage> pageList = new ArrayList<LimitValueReferencePage>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "稳态工况法");
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName ";
		List<LimitValueReference> referenceList = referenceDao.find(hql,
				params, referencePage.getPage(), referencePage.getRows());
		dg.setTotal(referenceDao.count("SELECT COUNT(*)" + hql, params));
		for (LimitValueReference reference : referenceList) {
			LimitValueReferencePage page = new LimitValueReferencePage();
			copyToPage(reference, page);
			page.setWTMaxRegisterTime(ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMaxRegisterTime()));
			page.setWTMinRegisterTime(ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMinRegisterTime()));
			page.setWTMaxBaseQuality(reference.getMaxBaseQuality());
			page.setWTMinBaseQuality(reference.getMinBaseQuality());
			pageList.add(page);
		}
		
		dg.setRows(pageList);
		return dg;
	}

	@Override
	public Json saveLimitValue(LimitValueReferencePage referencePage) {
		Json j = new Json();
		Timestamp minRegisterTime = null;
		Timestamp maxRegisterTime = null;
		
		if (referencePage.getWTMaxRegisterTime() != null
				&& !referencePage.getWTMaxRegisterTime().trim().equals("")
				&& referencePage.getWTMinRegisterTime() != null
				&& !referencePage.getWTMinRegisterTime().trim().equals("")) {
			maxRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getWTMaxRegisterTime());
			minRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getWTMinRegisterTime());
		}
		
		j = checkRegisterTime(minRegisterTime, maxRegisterTime);
		
		if (j.isSuccess() == false) {
			return j;
		}
		
		j = checkBaseQuality(referencePage.getWTMinBaseQuality(), referencePage.getWTMaxBaseQuality());
		if (j.isSuccess() == false) {
			return j;
		}
		
		LimitValueReference reference = new LimitValueReference();
		
		j = copyToReference(referencePage, reference);
		
		if (j.isSuccess() == false) {
			return j;
		}
		
		reference.setMaxRegisterTime(maxRegisterTime);
		reference.setMinRegisterTime(minRegisterTime);
		reference.setMaxBaseQuality(referencePage.getWTMaxBaseQuality());
		reference.setMinBaseQuality(referencePage.getWTMinBaseQuality());
		
		try {

			checkTimeBound(reference);
			checkQualityBound(reference);
			
			referenceDao.save(reference);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			return j;
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			return j;
		}
	}

	@Override
	public Json deleteLimitValue(LimitValueReferencePage referencePage) {
		Json json = new Json();
		if (referencePage.getIds() != null) {
			for (String id : referencePage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					LimitValueReference reference = referenceDao.get(
							LimitValueReference.class, Integer.valueOf(id));
					if (reference != null) {
						try {
							referenceDao.delete(reference);
						} catch (Exception e) {
							json.setMsg("删除不成功！");
							json.setSuccess(false);
							return json;
						}
						
					}else{
						json.setMsg("删除不成功！");
						json.setSuccess(false);
						return json;
					}
				}
			}
			json.setMsg("删除成功！");
			json.setSuccess(true);
			
		}else{
			json.setMsg("删除不成功！");
			json.setSuccess(true);
		}
		
		return json;
	}

	@Override
	public Json editLimitValue(LimitValueReferencePage referencePage) {
		Json j = new Json();
		
		Timestamp minRegisterTime = null;
		Timestamp maxRegisterTime = null;
		
		if (referencePage.getWTMaxRegisterTime() != null
				&& !referencePage.getWTMaxRegisterTime().trim().equals("")
				&& referencePage.getWTMinRegisterTime() != null
				&& !referencePage.getWTMinRegisterTime().trim().equals("")) {
			maxRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getWTMaxRegisterTime());
			minRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getWTMinRegisterTime());
		}
		
		j = checkRegisterTime(minRegisterTime, maxRegisterTime);
		
		if (j.isSuccess() == false) {
			return j;
		}
		
		j = checkBaseQuality(referencePage.getWTMinBaseQuality(), referencePage.getWTMaxBaseQuality());
		if (j.isSuccess() == false) {
			return j;
		}
		
		LimitValueReference reference = referenceDao.get(
				LimitValueReference.class, referencePage.getId());
		
		copyToReference(referencePage, reference);
		reference.setMaxRegisterTime(ChangeTimeFormat.getInstance()
				.strToTimeStamp(referencePage.getWTMaxRegisterTime()));
		reference.setMinRegisterTime(ChangeTimeFormat.getInstance()
				.strToTimeStamp(referencePage.getWTMinRegisterTime()));
		reference.setMaxBaseQuality(referencePage.getWTMaxBaseQuality());
		reference.setMinBaseQuality(referencePage.getWTMinBaseQuality());
		
		try {
			checkTimeBound(reference);
			checkQualityBound(reference);
			referenceDao.update(reference);
			j.setSuccess(true);
			j.setMsg("修改成功");
			return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败！");
			return j;
		}
	}

}
