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

@Service(value = "freeAccelerationLimitValueReferenceService")
public class FreeAccelerationLimitValueReferenceServiceImpl implements
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
		params.put("methodName", "自由加速法");
		String hql = "FROM DetectionMethodReference as d WHERE d.detectionMedhodName= :methodName";
		DetectionMethodReference method = methodDao.get(hql, params);
		if (method != null) {
			reference.setDetectionMethodReference(method);
			j.setSuccess(true);
		} else {

			j.setSuccess(false);
			j.setMsg("自由加速法参照信息尚未设置！");
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
		LimitValueReference beforeReference; // 起始时间之前所用限值参照
		LimitValueReference afterReference; // 结束时间之后所用限制参照
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName AND l.airInletMode= :airInletMode";
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("methodName", "自由加速法");
		param1.put("airInletMode", reference.getAirInletMode());
		param1.put("minRegisterTime", reference.getMinRegisterTime());
		String hql1 = hql
				+ " AND l.minRegisterTime< :minRegisterTime AND l.maxRegisterTime> :minRegisterTime";
		beforeReference = referenceDao.get(hql1, param1);
		if (beforeReference != null) {
			if (reference.getId() == null
					|| beforeReference.getId().intValue() != reference.getId()
							.intValue()) {
				beforeReference.setMaxRegisterTime(reference
						.getMinRegisterTime());
				referenceDao.update(beforeReference);
			}
		}
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("methodName", "自由加速法");
		param2.put("airInletMode", reference.getAirInletMode());
		param2.put("maxRegisterTime", reference.getMaxRegisterTime());
		String hql2 = hql
				+ " AND l.minRegisterTime< :maxRegisterTime AND l.maxRegisterTime> :maxRegisterTime";
		afterReference = referenceDao.get(hql2, param2);
		if (afterReference != null) {
			if (reference.getId() == null
					|| afterReference.getId().intValue() != reference.getId()
							.intValue()) {
				afterReference.setMinRegisterTime(reference
						.getMaxRegisterTime());
				referenceDao.update(afterReference);
			}
		}
	}

	@Override
	public DataGrid getLimitValues(LimitValueReferencePage referencePage) {
		DataGrid dg = new DataGrid();
		List<LimitValueReferencePage> pageList = new ArrayList<LimitValueReferencePage>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "自由加速法");
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName";
		List<LimitValueReference> referenceList = referenceDao.find(hql,
				params, referencePage.getPage(), referencePage.getRows());
		dg.setTotal(referenceDao.count("SELECT COUNT(*)" + hql, params));
		for (LimitValueReference reference : referenceList) {
			LimitValueReferencePage page = new LimitValueReferencePage();
			copyToPage(reference, page);
			page.setZJMaxRegisterTime(ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMaxRegisterTime()));
			page.setZJMinRegisterTime(ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMinRegisterTime()));
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
		if (referencePage.getZJMaxRegisterTime() != null
				&& !referencePage.getZJMaxRegisterTime().trim().equals("")
				&& referencePage.getZJMinRegisterTime() != null
				&& !referencePage.getZJMinRegisterTime().trim().equals("")) {
			maxRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getZJMaxRegisterTime());
			minRegisterTime = ChangeTimeFormat.getInstance().strToTimeStamp(
					referencePage.getZJMinRegisterTime());
		}
		j = checkRegisterTime(minRegisterTime, maxRegisterTime);
		if (j.isSuccess() == false)
			return j;
		LimitValueReference reference1 = new LimitValueReference();
		j = copyToReference(referencePage, reference1);
		if (j.isSuccess() == false)
			return j;
		reference1.setMaxRegisterTime(maxRegisterTime);
		reference1.setMinRegisterTime(minRegisterTime);
		// 所选进气方式不限时，需添加两条记录分别对应两种进气方式
		if (referencePage.getAirInletMode().equals("null")) {
			LimitValueReference reference2 = new LimitValueReference();
			copyToReference(referencePage, reference2);
			reference2.setMaxRegisterTime(maxRegisterTime);
			reference2.setMinRegisterTime(minRegisterTime);
			reference2.setAirInletMode("涡轮增压");
			checkTimeBound(reference2);
			referenceDao.save(reference2);
			reference1.setAirInletMode("自然吸气");
		}
		try {
			checkTimeBound(reference1);
			referenceDao.save(reference1);
			j.setSuccess(true);
			j.setMsg("添加成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
		}
		return j;
	}

	@Override
	public Json deleteLimitValue(LimitValueReferencePage referencePage) {
		Json j = new Json();
		try {
			if (referencePage.getIds() != null) {
				for (String id : referencePage.getIds().split(",")) {
					if (id != null && !id.trim().equals("")) {
						LimitValueReference reference = referenceDao.get(
								LimitValueReference.class, Integer.valueOf(id));
						if (reference != null) {
							referenceDao.delete(reference);
						} else {
							j.setSuccess(false);
							j.setMsg("未找到对应限值记录！");
						}
					}
				}
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
			return j;
		}
		return j;
	}

	@Override
	public Json editLimitValue(LimitValueReferencePage referencePage) {
		Json j = new Json();
		LimitValueReference reference = referenceDao.get(
				LimitValueReference.class, referencePage.getId());
		try {
			if (!ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMaxRegisterTime())
					.equals(referencePage.getZJMaxRegisterTime())
					|| !ChangeTimeFormat.getInstance()
							.timeStampToString(reference.getMinRegisterTime())
							.equals(referencePage.getZJMinRegisterTime())) {
				checkRegisterTime(reference.getMinRegisterTime(),
						reference.getMaxRegisterTime());
			}
			copyToReference(referencePage, reference);
			reference.setMaxRegisterTime(ChangeTimeFormat.getInstance()
					.strToTimeStamp(referencePage.getZJMaxRegisterTime()));
			reference.setMinRegisterTime(ChangeTimeFormat.getInstance()
					.strToTimeStamp(referencePage.getZJMinRegisterTime()));
			checkTimeBound(reference);
			referenceDao.update(reference);
			j.setSuccess(true);
			j.setMsg("修改成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
		}
		return j;
	}
}
