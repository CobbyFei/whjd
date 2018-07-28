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
import com.dbsoft.whjd.model.EnvironmentalLabel;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.NewCarCertificateSheet;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.NewCarAdmitSheetPage;
import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.service.INewCarAdmitService;
import com.dbsoft.whjd.util.ChangeTimeFormat;

@Service(value = "newCarAdmitService")
public class NewCarAdmitServiceImpl implements INewCarAdmitService {
	private IBaseDao<NewCarCertificateSheet> newCarCertificateSheetDao;
	 
	@Override
	public DataGrid getNewCarAdmitSheet(NewCarAdmitSheetPage newCarAdmitSheetPage) throws Exception {
		List<NewCarAdmitSheetPage> newCarCertificateSheetPages = new ArrayList<NewCarAdmitSheetPage>();

		String hql = "FROM NewCarCertificateSheet as nca WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();

		// 取出session中的用户信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		String stationName = (String) httpSession.getAttribute("stationName");

		// 增加where
		if (newCarAdmitSheetPage != null) {
			if (stationName == null) {
				throw new Exception(); // 如果 sessionStationId 为空 则抛异常！
			}
			if (!stationName.equals("市局")) { // 如果不是局里的就就显示他本站，如果是局里的就什么也不做
				newCarAdmitSheetPage.setFillStationName(stationName);
			}
			hql = this.addSysUserWhere(newCarAdmitSheetPage, hql, params);
			

		} else {
			throw new Exception(); // 出现了特殊情况会在Action 中抛异常
		}

		DataGrid dg = new DataGrid();

		dg.setTotal(newCarCertificateSheetDao.count("SELECT COUNT(*) " + hql, params));

		hql = hql+" order by nca.id desc";
		List<NewCarCertificateSheet> tMaps = newCarCertificateSheetDao.find(hql, params, newCarAdmitSheetPage.getPage(), newCarAdmitSheetPage.getRows());
		if (tMaps != null && tMaps.size() > 0) {
			for (NewCarCertificateSheet t : tMaps) {
				NewCarAdmitSheetPage m = new NewCarAdmitSheetPage();
				BeanUtils.copyProperties(t, m, new String[] {});

				newCarCertificateSheetPages.add(m);
			}
		}

		dg.setRows(newCarCertificateSheetPages);
		return dg;
	}

	/*
	 * 拼接搜索条件
	 */
	private String addSysUserWhere(NewCarAdmitSheetPage newCarAdmitSheetPage, String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		String fillStationName = newCarAdmitSheetPage.getFillStationName();
		String certificateID = newCarAdmitSheetPage.getCertificateID();
		String vehicleOwnerName = newCarAdmitSheetPage.getVehicleOwnerName();
		Integer emissionStandard = newCarAdmitSheetPage.getEmissionStandard();
		String vin = newCarAdmitSheetPage.getVin();
		String engineCode = newCarAdmitSheetPage.getEngineCode();
		String issuBeginTime = newCarAdmitSheetPage.getIssueBeginTime();
		String issuEndTime = newCarAdmitSheetPage.getIssueEndTime();
		String licence = newCarAdmitSheetPage.getLicence();
		Integer vehicleType = newCarAdmitSheetPage.getVehicleType();
		
		if (fillStationName != null
				&& !fillStationName.trim().equals("")) {
			params.put("fillStationName", fillStationName);
			hql += " AND nca.fillStationName= :fillStationName";
		}
		if (certificateID != null
				&& !certificateID.trim().equals("")) {
			params.put("certificateID", certificateID);
			hql += " AND nca.certificateID= :certificateID";
		}
		if (vehicleOwnerName != null
				&& !vehicleOwnerName.trim().equals("")) {
			params.put("vehicleOwnerName", vehicleOwnerName);
			hql += " AND nca.vehicleOwnerName= :vehicleOwnerName";
		}
		if (emissionStandard != null
				&& emissionStandard>0) {
			params.put("emissionStandard", emissionStandard);
			hql += " AND nca.emissionStandard= :emissionStandard";
		}
		if (vin != null
				&& !vin.trim().equals("")) {
			params.put("vin", vin);
			hql += " AND nca.vin= :vin";
		}
		if (engineCode != null
				&& !engineCode.trim().equals("")) {
			params.put("engineCode", engineCode);
			hql += " AND nca.engineCode= :engineCode";
		}
		if (issuBeginTime != null
				&& !issuBeginTime.trim().equals("")) {
			params.put("issuBeginTime", issuBeginTime);
			hql += " AND nca.vehicleIssueCertDate >= :issuBeginTime";
		}
		if (issuEndTime != null
				&& !issuEndTime.trim().equals("")) {
			params.put("issuEndTime", issuEndTime);
			hql += " AND nca.vehicleIssueCertDate <= :issuEndTime";
		}
		if (licence != null
				&& !licence.trim().equals("")) {
			params.put("licence", licence);
			hql += " AND nca.licence= :licence";
		}
		if (vehicleType != null
				&& vehicleType!=-1) {
			params.put("vehicleType", vehicleType);
			hql += " AND nca.vehicleType= :vehicleType";
		}
		return hql;
		
	}
	
	

	@Override
	public Json changeNewCarAdmitStatus(NewCarAdmitSheetPage newCarAdmitSheetPage) {
		String ids = newCarAdmitSheetPage.getIds();
		Integer changeToStatus = newCarAdmitSheetPage.getChangeToStatus();
		Json json = new Json();
		
		if(ids!=null && ids!="" && changeToStatus!=null && changeToStatus >= 0 && changeToStatus <=1 ){

			String[] strIds= ids.split(",");
			
			for(int i = 0; i < strIds.length; i++){
				NewCarCertificateSheet newCarCertificateSheet = newCarCertificateSheetDao.get(NewCarCertificateSheet.class, Integer.parseInt(strIds[i].trim()));
				
				if (newCarCertificateSheet == null) {
					json.setMsg("操作失败！");
					json.setSuccess(false);
					return json;
				}
				
				newCarCertificateSheet.setStatus(changeToStatus);
				
				try{		
					newCarCertificateSheetDao.update(newCarCertificateSheet);
				}catch(Exception e){
					json.setMsg("操作失败！");
					json.setSuccess(false);
					return json;
				}
				
			}
			json.setMsg("操作成功！");
			json.setSuccess(true);
			
			return json;
			
		}else{
			json.setMsg("操作失败！");
			json.setSuccess(false);
			
			return json;
		}
	}

	
	@Override
	public NewCarAdmitSheetPage gerNewCarAdmitSheetById(Integer id) {
		NewCarAdmitSheetPage ePage=new NewCarAdmitSheetPage();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("id", id);
		String hql = "FROM NewCarCertificateSheet as ncc where ncc.ID=:id";
		NewCarCertificateSheet newCarCertificateSheet = newCarCertificateSheetDao.get(hql,tMap);
		if (newCarCertificateSheet!=null) {
			BeanUtils.copyProperties(newCarCertificateSheet, ePage);
		    //statusToStr(eLabel, ePage);
		    //timeToStr(eLabel, ePage);
		}
		return ePage;
	}

	public IBaseDao<NewCarCertificateSheet> getNewCarCertificateSheetDao() {
		return newCarCertificateSheetDao;
	}
	@Resource(name="baseDao")
	public void setNewCarCertificateSheetDao(IBaseDao<NewCarCertificateSheet> newCarCertificateSheetDao) {
		this.newCarCertificateSheetDao = newCarCertificateSheetDao;
	}

	
}
