package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import sun.tools.jar.resources.jar;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionMethodReference;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.TrimString;

@Service("lugdownLimitValueService")
public class LugdownLimitValueServiceImpl implements ILimitValueReferenceService{
   
	private IBaseDao<LimitValueReference> referenceDao;
	private IBaseDao<DetectionMethodReference> methodDao;
	
	
	
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
		Json res=new  Json();
		
		BeanUtils.copyProperties(page, reference);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "加载减速法");
		String hql = "FROM DetectionMethodReference as d WHERE d.detectionMedhodName= :methodName";
		DetectionMethodReference method = methodDao.get(hql, params);
		if(method!=null)
		{
		  reference.setDetectionMethodReference(method);
		  res.setSuccess(true);
		  return res;
		}
		return res;
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
	 * 添加限值时，需要对已有限值的作用时间进行修正，保证时间上能衔接
	 * 
	 * @author gsw
	 */
	public void checkTimeBound(LimitValueReference reference) {
		LimitValueReference beforeReference; // 起始时间之前所用限值参照
		LimitValueReference afterReference; // 结束时间之后所用限制参照
		String hql = "FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName= :methodName and l.vehicleDetailType=:vehicleType ";
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("methodName", "加载减速法");
		param1.put("minRegisterTime", reference.getMinRegisterTime());
		param1.put("vehicleType", reference.getVehicleDetailType());
		String hql1 = hql
				+ " AND l.minRegisterTime< :minRegisterTime AND l.maxRegisterTime> :minRegisterTime ";
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
		param2.put("methodName", "加载减速法");
		param2.put("maxRegisterTime", reference.getMaxRegisterTime());
		param2.put("vehicleType", reference.getVehicleDetailType());
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
		params.put("methodName", "加载减速法");
		String hql = " FROM LimitValueReference as l WHERE l.detectionMethodReference.detectionMedhodName=:methodName";
		List<LimitValueReference> referenceList = referenceDao.find(hql,
				params, referencePage.getPage(), referencePage.getRows());
		dg.setTotal(referenceDao.count("SELECT COUNT(*)" + hql, params));
		for (LimitValueReference reference : referenceList) {
			LimitValueReferencePage page = new LimitValueReferencePage();
			copyToPage(reference, page);
			page.setJZMaxRegisterTime(ChangeTimeFormat.getInstance()
					.timeStampToString(reference.getMaxRegisterTime()));
			page.setJZMinRegisterTime(ChangeTimeFormat.getInstance().timeStampToString(reference.getMinRegisterTime()));
			pageList.add(page);
		}
		dg.setRows(pageList);
		return dg;
	}

	@Override
	public Json saveLimitValue(LimitValueReferencePage referencePage) {
		Json res=new Json();
		res.setMsg("无数据传入");
		if(referencePage!=null)
		{
			try {
				referencePage=(LimitValueReferencePage)TrimString.getInstance().trimObjectString(referencePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Timestamp minRegisterTime = null;
			Timestamp maxRegisterTime = null;
			if(referencePage.getJZMinRegisterTime()!=null&&!referencePage.getJZMinRegisterTime().equals("")&&referencePage.getJZMaxRegisterTime()!=null&&!referencePage.getJZMaxRegisterTime().equals(""))
			{
				minRegisterTime=Timestamp.valueOf(referencePage.getJZMinRegisterTime()+" 00:00:00");
				maxRegisterTime=Timestamp.valueOf(referencePage.getJZMaxRegisterTime()+" 00:00:00");
			}
			res=checkRegisterTime(minRegisterTime, maxRegisterTime);
			if(res.isSuccess()==false)
			{
				return res;
			}
			LimitValueReference reference=new LimitValueReference();
			res=copyToReference(referencePage, reference);
			if(res.isSuccess()==false)
			{
				res.setMsg("查找不到对应的方法!");
				return res;
			}
			reference.setMaxRegisterTime(maxRegisterTime);
			reference.setMinRegisterTime(minRegisterTime);
			checkTimeBound(reference);
			referenceDao.save(reference);
			res.setSuccess(true);
			res.setMsg("添加加载减速法的限值成功!");
		}
		return res;
	}

	@Override
	public Json deleteLimitValue(LimitValueReferencePage referencePage) {
		Json res=new Json();
	
		try {
			if (referencePage.getIds() != null && !referencePage.getIds().equals("")) {
				for (String id : referencePage.getIds().split(",")) {
					if (id != null && !id.trim().equals("")) {
						LimitValueReference reference = referenceDao.get(
								LimitValueReference.class, Integer.valueOf(id));
						if (reference != null) {
							referenceDao.delete(reference);
						}
					}
				}
				res.setSuccess(true);
				res.setMsg("删除加载减速法限值成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			res.setMsg("删除记录失败!");
		}
		return res;
	}

	@Override
	public Json editLimitValue(LimitValueReferencePage referencePage) {
		Json res=new Json();
		System.out.println(referencePage.getId());
		LimitValueReference reference=referenceDao.get(LimitValueReference.class,referencePage.getId());
		res=copyToReference(referencePage, reference);
		if(res.isSuccess()==false)
		{
			res.setSuccess(false);
			res.setMsg("对应的方法不存在,修改限值无效");
			return res;
		}
		reference.setMaxRegisterTime(Timestamp.valueOf(referencePage.getJZMaxRegisterTime()+" 00:00:00"));
		reference.setMinRegisterTime(Timestamp.valueOf(referencePage.getJZMinRegisterTime()+" 00:00:00"));
		res=checkRegisterTime(reference.getMinRegisterTime(), reference.getMaxRegisterTime());
		if(res.isSuccess()==false)
		{
			return res;
		}
		checkTimeBound(reference);
		try {
			referenceDao.update(reference);
			res.setSuccess(true);
			res.setMsg("修改限值成功!");
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg("修改限值记录出现异常");
			res.setSuccess(false);
		}
		return res;
	}

	public IBaseDao<LimitValueReference> getReferenceDao() {
		return referenceDao;
	}
    @Resource(name="baseDao")
	public void setReferenceDao(IBaseDao<LimitValueReference> referenceDao) {
		this.referenceDao = referenceDao;
	}

	public IBaseDao<DetectionMethodReference> getMethodDao() {
		return methodDao;
	}
    @Resource(name="baseDao")
	public void setMethodDao(IBaseDao<DetectionMethodReference> methodDao) {
		this.methodDao = methodDao;
	}
	

}
