package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionMethodReference;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionMethodReferencePage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionMethodReferenceService;
import com.dbsoft.whjd.util.TrimString;

@Service("detectionMethodReferenceService")
public class DetectionMethodReferenceImpl implements
		IDetectionMethodReferenceService {
	private IBaseDao<DetectionMethodReference> detectionMethodReferenceDao;
	
	public IBaseDao<DetectionMethodReference> getDetectionMethodReferenceDao() {
		return detectionMethodReferenceDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionMethodReferenceDao(
			IBaseDao<DetectionMethodReference> detectionMethodReferenceDao) {
		this.detectionMethodReferenceDao = detectionMethodReferenceDao;
	}

	/**
	 * 添加检测方法参照记录
	 */
	@Override
	public Json addDetectionMethodReference(
			DetectionMethodReferencePage detectionMethodReferencePage) {
		Json json=new Json();
		try {
			detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DetectionMethodReference detectionMethodReference=new DetectionMethodReference();
		BeanUtils.copyProperties(detectionMethodReferencePage, detectionMethodReference);
		try{
			detectionMethodReferenceDao.save(detectionMethodReference);
			json.setSuccess(true);
			json.setMsg("添加检测方法参考记录成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("添加检测方法参考记录失败");
		}
		return json;
	}
	
	/**
	 * 删除检测方法对应的记录
	 */
	@Override
	public Json deleteDetecitonMethodReference(
			DetectionMethodReferencePage detectionMethodReferencePage) {
		try {
			detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json res=new Json();
		String flag="";
		if(detectionMethodReferencePage.getIds()!=null && !detectionMethodReferencePage.getIds().equals(""))
		{
			try{
				 for(String id:detectionMethodReferencePage.getIds().trim().split(","))
				 {
					 flag=id;
					 String hql=" from DetectionMethodReference as dmr where dmr.id=:id";
					 Map<String, Object> tMap=new HashMap<String, Object>();
					 tMap.put("id", Integer.parseInt(id));
					 DetectionMethodReference dmr=detectionMethodReferenceDao.get(hql,tMap);
					 detectionMethodReferenceDao.delete(dmr);
				 }
				 res.setSuccess(true);
				 res.setMsg("删除检测线信息成功");
				
			}catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("删除id为"+flag+"的检测方法参照信息失败");
				return res;
			}
		}
	    return res;
	}

	@Override
	public DataGrid getAllDetectionMethodReference(
			DetectionMethodReferencePage detectionMethodReferencePage) {
		DataGrid dg=new DataGrid();
		String hql=" from DetectionMethodReference as dmr where 1=1 ";
		Map<String, Object> tMap=new HashMap<String, Object>();
		if(detectionMethodReferencePage!=null)
		{
			try {
				detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//动态的拼接sql语句
			
			if(detectionMethodReferencePage.getDetectionMedhodName()!=null && !detectionMethodReferencePage.getDetectionMedhodName().equals(""))
			{
				System.out.println(detectionMethodReferencePage.getDetectionMedhodName());
				hql+=" and dmr.detectionMedhodName like :methodName";
				tMap.put("methodName", "%"+detectionMethodReferencePage.getDetectionMedhodName()+"%");
			}
		}
		//获取总数量
		dg.setTotal(detectionMethodReferenceDao.count("select count(*) "+hql,tMap));

		List<DetectionMethodReference> list=detectionMethodReferenceDao.find(hql,tMap,detectionMethodReferencePage.getPage(),detectionMethodReferencePage.getRows());
	
		List<DetectionMethodReferencePage> dmrList=new ArrayList<DetectionMethodReferencePage>();
		if(list!=null && list.size()>0)
		{
			
			for(DetectionMethodReference l:list)
			{
				DetectionMethodReferencePage d=new DetectionMethodReferencePage();
				try {
					l=(DetectionMethodReference)TrimString.getInstance().trimObjectString(l);
				} catch (Exception e) {
					e.printStackTrace();
				}
				BeanUtils.copyProperties(l, d);
				dmrList.add(d);
			}
		}
		dg.setRows(dmrList);
		
		return dg;
	}

	@Override
	public Json modifyDetectionMethodReference(
			DetectionMethodReferencePage detectionMethodReferencePage) {
		Json res=new Json();
		if(detectionMethodReferencePage!=null)
		{
			try {
				detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql=" from DetectionMethodReference as dmr where dmr.id="+detectionMethodReferencePage.getId();
			DetectionMethodReference dmr=detectionMethodReferenceDao.find(hql).get(0);
			BeanUtils.copyProperties(detectionMethodReferencePage, dmr);
			try{
				detectionMethodReferenceDao.update(dmr);
				res.setSuccess(true);
				res.setMsg("修改信息成功");
			}catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("修改检测方法信息失败");
				return res;
			}
		}
		return res;
	}

	@Override
	public List<DetectionMethodReferencePage> getAllDetectionMethodReferenceList(DetectionMethodReferencePage detectionMethodReferencePage) {
		String hql=" from DetectionMethodReference as dmr where 1=1 ";
		Map<String, Object> tMap=new HashMap<String, Object>();
		if(detectionMethodReferencePage!=null)
		{
			try {
				detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//动态的拼接sql语句
			
			if(detectionMethodReferencePage.getQ()!=null && !detectionMethodReferencePage.getQ().equals(""))
			{
				hql+=" and dmr.detectionMedhodName like :methodName";
				tMap.put("methodName", "%"+detectionMethodReferencePage.getQ()+"%");
			}
		}
		List<DetectionMethodReference> list=detectionMethodReferenceDao.find(hql,tMap,detectionMethodReferencePage.getPage(),detectionMethodReferencePage.getRows());
		List<DetectionMethodReferencePage> dmrList=new ArrayList<DetectionMethodReferencePage>();
		if(list!=null && list.size()>0)
		{
			
			for(DetectionMethodReference l:list)
			{
				DetectionMethodReferencePage d=new DetectionMethodReferencePage();
				try {
					l=(DetectionMethodReference)TrimString.getInstance().trimObjectString(l);
				} catch (Exception e) {
					e.printStackTrace();
				}
				BeanUtils.copyProperties(l, d);
				dmrList.add(d);
			}
		}
		return dmrList;
	}

	@Override
	public Json hasMethodName(
			DetectionMethodReferencePage detectionMethodReferencePage) {
		Json json=new Json();
		Map<String, Object> tMap=new HashMap<String, Object>();
		String hql=" from DetectionMethodReference as dmr where 1=1 ";
		if(detectionMethodReferencePage.getQ()!=null && !detectionMethodReferencePage.getQ().trim().equals(""))
		{
		     tMap.put("methodName", detectionMethodReferencePage.getQ().trim());
		     hql+=" and dmr.detectionMedhodName=:methodName";
		}
		List<DetectionMethodReference> list=detectionMethodReferenceDao.find(hql, tMap);
		
		if(list!=null && list.size()>0)
		{
			DetectionMethodReference dl=list.get(0);
			if(detectionMethodReferencePage.getId()!=null)
			{
				
				if(detectionMethodReferencePage.getId()==dl.getId())
				{
					json.setSuccess(false);
					json.setMsg("方法名称可用");
				}
				else {
					json.setSuccess(true);
					json.setMsg("方法名称不可用");
				}
			}
			else{
				json.setSuccess(true);
				json.setMsg("方法名称不可用");
			}
		}
		else {
			json.setSuccess(false);
			json.setMsg("方法名称可用");
		}
		return json;
	}

	@Override
	public Json getDetectionMethod(
			DetectionMethodReferencePage detectionMethodReferencePage) {
	    Json res=new Json();
	    if(detectionMethodReferencePage!=null)
	    {
	    	String hql="from DetectionMethodReference as dmr where 1=1 ";
	    	try {
				detectionMethodReferencePage=(DetectionMethodReferencePage)TrimString.getInstance().trimObjectString(detectionMethodReferencePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	if(detectionMethodReferencePage.getFuelType()!=null &&!detectionMethodReferencePage.getFuelType().equals(""))
	    	{
	    		if(detectionMethodReferencePage.getFuelType().equals("汽油")||detectionMethodReferencePage.getFuelType().equals("天然气"))
	    		{
	    		    hql+=" and dmr.weightMin<=:weight and dmr.weightMax>:weight and dmr.fuelType like '%汽油%'";
	    		    Map<String, Object> tMap=new HashMap<String, Object>();
	    		    tMap.put("weight", detectionMethodReferencePage.getMaxTotalQuality());
	    		    List<DetectionMethodReference> list=detectionMethodReferenceDao.find(hql,tMap);
	    		    if(list!=null && list.size()>0)
	    		    {
	    		    	DetectionMethodReference detectionMethodReference=list.get(0);
	    		    	res.setSuccess(true);
	    		    	res.setMsg(detectionMethodReference.getDetectionMedhodName().trim());
	    		    }
	    		    else {
						res.setSuccess(false);
						res.setMsg("查不到对应检测方法");
						return res;
					}
	    		}
	    		else {
	    			  hql+=" and dmr.weightMin<=:weight and dmr.weightMax>:weight and dmr.lengthMin<=:length and dmr.lengthMax>:length and dmr.fuelType like '%柴油%'";
	    			  Map<String, Object> tMap=new HashMap<String, Object>();
	    			  tMap.put("weight", detectionMethodReferencePage.getMaxTotalQuality());
	    			  tMap.put("length", detectionMethodReferencePage.getVehicleLength());
	    			  List<DetectionMethodReference> list=detectionMethodReferenceDao.find(hql,tMap);
	    			  if(list!=null && list.size()>0)
	    			  {
	    				  DetectionMethodReference detectionMethodReference=list.get(0);
	    				  if(detectionMethodReference.getLengthMin()<=0)
	    				  {
	    					  res.setSuccess(true);
	    					  res.setMsg("加载减速法");
	    					  return res;
	    				  }
	    				  else {
							   res.setMsg("自由加速法");
							   res.setSuccess(true);
							   return res;
						 }
	    			  }
	    			  else {
						res.setSuccess(true);
						res.setMsg("自由加速法");
						return res;
					}
				}
	    	}
	    	else{
	    		res.setMsg("请选择燃油类型");
	    		res.setSuccess(false);
	    		return res;
	    	}
	    }
	    
		return res;
	}

}
