package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.annotation.Resource;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.EmissionAdmitStandard;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EmissionAdmitStandardPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IAdmitStandardService;
import com.dbsoft.whjd.util.ChangeTimeFormat;

@Service(value = "admitstandardService")
public class AdmitstandardServiceImpl implements IAdmitStandardService {
	
	private IBaseDao<EmissionAdmitStandard> emissionAdmitStandardDao;

	@Override
	public DataGrid findAdmitStandards(EmissionAdmitStandardPage emissionAdmitStandardPage) {
		DataGrid dg = new DataGrid();
		List<EmissionAdmitStandardPage> emissionAdmitStandardPages = new ArrayList<EmissionAdmitStandardPage>();
		String hql = "FROM EmissionAdmitStandard as su WHERE 1=1 ";
		dg.setTotal(emissionAdmitStandardDao.count("SELECT COUNT(*) " + hql));
		
		List<EmissionAdmitStandard> tMaps = emissionAdmitStandardDao.find(hql, emissionAdmitStandardPage.getPage(), emissionAdmitStandardPage.getRows());
		
		if(tMaps!=null && tMaps.size()>0){
			for(EmissionAdmitStandard t : tMaps){
				EmissionAdmitStandardPage m = new EmissionAdmitStandardPage();
				BeanUtils.copyProperties(t,m, new String[]{});
				
				emissionAdmitStandardPages.add(m);
			}	
		}
		dg.setRows(emissionAdmitStandardPages);
		return dg;
	}
	
	

	@Override
	public Json updateAdmitStandard(EmissionAdmitStandardPage emissionAdmitStandardPage) {
		
		Json json = new Json();
		
		EmissionAdmitStandard emissionAdmitStandard =  emissionAdmitStandardDao.get(EmissionAdmitStandard.class, emissionAdmitStandardPage.getID());
		emissionAdmitStandard.setCLLB(emissionAdmitStandardPage.getCLLB());
		emissionAdmitStandard.setYT(emissionAdmitStandardPage.getYT());
		emissionAdmitStandard.setZRBZ(emissionAdmitStandardPage.getZRBZ());
		emissionAdmitStandard.setSSSJ(emissionAdmitStandardPage.getSSSJ());
		
		try{		
			emissionAdmitStandardDao.update(emissionAdmitStandard);
			json.setMsg("更新成功！");
			json.setSuccess(true);
			
		}catch(Exception e){
			json.setMsg("更新失败！");
			json.setSuccess(false);
		}
		
		return json;
		
	}



	@Override
	public Json addAdmitStandard(EmissionAdmitStandardPage emissionAdmitStandardPage) {
		Json json = new Json();
		
		EmissionAdmitStandard emissionAdmitStandard = new EmissionAdmitStandard();
		emissionAdmitStandard.setCLLB(emissionAdmitStandardPage.getCLLB());
		emissionAdmitStandard.setYT(emissionAdmitStandardPage.getYT());
		emissionAdmitStandard.setZRBZ(emissionAdmitStandardPage.getZRBZ());
		emissionAdmitStandard.setSSSJ(emissionAdmitStandardPage.getSSSJ());
		try{		
			emissionAdmitStandardDao.save(emissionAdmitStandard);
			json.setMsg("新增成功！");
			json.setSuccess(true);
			
		}catch(Exception e){
			json.setMsg("新增失败！");
			json.setSuccess(false);
		}
		
		return json;
	}



	@Override
	public Json deleteAdmitStandardValue(EmissionAdmitStandardPage emissionAdmitStandardPage) {
		Json j = new Json();
		try {
			if (emissionAdmitStandardPage.getIds() != null) {
				for (String id : emissionAdmitStandardPage.getIds().split(",")) {
					if (id != null && !id.trim().equals("")) {
						EmissionAdmitStandard emissionAdmitStandard = emissionAdmitStandardDao.get(
								EmissionAdmitStandard.class, Integer.valueOf(id));
						if (emissionAdmitStandard != null) {
							emissionAdmitStandardDao.delete(emissionAdmitStandard);
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



	public IBaseDao<EmissionAdmitStandard> getEmissionAdmitStandardDao() {
		return emissionAdmitStandardDao;
	}
	@Resource(name="baseDao")
	public void setEmissionAdmitStandardDao(IBaseDao<EmissionAdmitStandard> emissionAdmitStandardDao) {
		this.emissionAdmitStandardDao = emissionAdmitStandardDao;
	}

	
}
