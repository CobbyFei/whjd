package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.EmissionAdmitStandard;
import com.dbsoft.whjd.model.EnvironmentalLabel;
import com.dbsoft.whjd.model.NewCarCertificateSheet;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.VehicleTypeInfo;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.EmissionAdmitStandardPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.NewCarAdmitSheetPage;
import com.dbsoft.whjd.pageModel.VehicleTypeInfoPage;
import com.dbsoft.whjd.service.IEmissionStandardService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.TrimString;

@Service("emissionStandardService")
public class EmissionStandardServiceImpl implements IEmissionStandardService {

	private IBaseDao<VehicleTypeInfo> vehicleTypeDao;
	private IBaseDao<EmissionAdmitStandard> emissionAdmitStandardDao;
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<NewCarCertificateSheet> newCarCertificateSheetDao;
	private IBaseDao<SysUser> sysUserDao;
	
	
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	public IBaseDao<NewCarCertificateSheet> getNewCarCertificateSheetDao() {
		return newCarCertificateSheetDao;
	}
	@Resource(name = "baseDao")
	public void setNewCarCertificateSheetDao(IBaseDao<NewCarCertificateSheet> newCarCertificateSheetDao) {
		this.newCarCertificateSheetDao = newCarCertificateSheetDao;
	}

	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}
	
	public IBaseDao<EmissionAdmitStandard> getEmissionAdmitStandardDao() {
		return emissionAdmitStandardDao;
	}
	@Resource(name = "baseDao")
	public void setEmissionAdmitStandardDao(IBaseDao<EmissionAdmitStandard> emissionAdmitStandardDao) {
		this.emissionAdmitStandardDao = emissionAdmitStandardDao;
	}
	public IBaseDao<VehicleTypeInfo> getVehicleTypeDao() {
		return vehicleTypeDao;
	}
	@Resource(name = "baseDao")
	public void setVehicleTypeDao(IBaseDao<VehicleTypeInfo> vehicleTypeDao) {
		this.vehicleTypeDao = vehicleTypeDao;
	}

	@Override
	public Json judgeAdmit(VehicleTypeInfoPage vehicleTypePage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPF(VehicleTypeInfoPage vehicleTypeInfoPage) {
		// TODO Auto-generated method stub
		String hql = "FROM VehicleTypeInfo as VT where VT.FILENAME = "
				+ "(select max(FILENAME) from VehicleTypeInfo as v "
				+ "where v.CLXH =:CLXH and v.FDJXH =:FDJXH and v.FILENAME <= :FILENAME)"
				+ "and VT.CLXH =:CLXH and VT.FDJXH =:FDJXH and VT.FILENAME <= :FILENAME";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CLXH", vehicleTypeInfoPage.getCLXH().trim());
		params.put("FDJXH", vehicleTypeInfoPage.getFDJXH().trim());
		params.put("FILENAME", vehicleTypeInfoPage.getFILENAME());
		VehicleTypeInfo vehicletype = null;
		vehicletype = vehicleTypeDao.get(hql, params);
		if(vehicletype==null)
			return null;
		String PF = vehicletype.getPF();
		return PF;
	}
	@Override
	public String getZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage) {
		// TODO Auto-generated method stub
		
		String CLLB = null;
		float maxQuality = Float.parseFloat(vehicleTypeInfoPage.getMaxQuality());
		String fuelType=vehicleTypeInfoPage.getFuelType();
		if(fuelType.equals("天然气")){
			CLLB="天然气车";
		}else if(fuelType.equals("混合动力")){
			CLLB="混合动力车";
		}else {
		
		if(maxQuality>3500)
			CLLB = "重型"+vehicleTypeInfoPage.getFuelType()+"车";
		else
			CLLB = "轻型"+vehicleTypeInfoPage.getFuelType()+"车";
		}
		String hql = "FROM EmissionAdmitStandard as EAS where EAS.SSSJ = "
				+ "(select max(SSSJ) from EmissionAdmitStandard as e "
				+ "where e.CLLB =:CLLB and (e.YT =:YT or e.YT like '不论何种用途') and e.SSSJ <= :FILENAME)"
				+ "and EAS.CLLB =:CLLB and (EAS.YT =:YT or EAS.YT like '不论何种用途') and EAS.SSSJ <= :FILENAME";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CLLB", CLLB);
		params.put("YT", vehicleTypeInfoPage.getApplication());
		
		Date dt=new Date();
	    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println("今天的日期是"+ matter1.format(dt));
		params.put("FILENAME", matter1.format(dt));
		
		EmissionAdmitStandard emissionAdmit = null;
		emissionAdmit = emissionAdmitStandardDao.get(hql, params);
		if(emissionAdmit==null)
			return null;
		String ZRBZ = emissionAdmit.getZRBZ()+"";
		return ZRBZ;
	}

	
	//获取在用车准入标准查询，2018-01-02添加,2018-2-3注释,2018-2-23重新添加
	public String getUsingCarZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage) {
		
		String ZRBZ = null;
		String fuelType=vehicleTypeInfoPage.getFuelType();
		System.out.println("燃油类型是"+ fuelType);
		if(fuelType.equals("汽油")){
			ZRBZ="1";
		}else if(fuelType.equals("柴油")){
			ZRBZ="3";
		}else {
			ZRBZ="4";
		}	
		return ZRBZ;
	}
	
	@Override
	public String getMotorZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage) {
		// TODO Auto-generated method stub
		
		String CLLB = "摩托车";
//		float maxQuality = Float.parseFloat(vehicleTypeInfoPage.getMaxQuality());
	//	if(maxQuality>3500)
//			CLLB = "重型"+vehicleTypeInfoPage.getFuelType()+"车";
//		else
//			CLLB = "轻型"+vehicleTypeInfoPage.getFuelType()+"车";
		
		String hql = "FROM EmissionAdmitStandard as EAS where EAS.SSSJ = "
				+ "(select max(SSSJ) from EmissionAdmitStandard as e "
				+ "where e.CLLB =:CLLB  and e.SSSJ <= :FILENAME)"
				+ "and EAS.CLLB =:CLLB  and EAS.SSSJ <= :FILENAME";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CLLB", CLLB);
	
		
		Date dt=new Date();
	    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println("今天的日期是"+ matter1.format(dt));
		params.put("FILENAME", matter1.format(dt));
		
		EmissionAdmitStandard emissionAdmit = null;
		emissionAdmit = emissionAdmitStandardDao.get(hql, params);
		if(emissionAdmit==null)
			return null;
		String ZRBZ = emissionAdmit.getZRBZ()+"";
		System.out.print(ZRBZ);
		return ZRBZ;
	}
	
	@Override
	public void addNewCarAdmitInfo(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		// TODO Auto-generated method stub
		
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			NewCarCertificateSheet newCarCertificateSheet = new NewCarCertificateSheet();
			BeanUtils.copyProperties(detectionCommisionSheetPage, newCarCertificateSheet, new String[] { "vehicleRegisterDate","vechileIssueCertTime" });
			// 设置汽车登记时间
			if (detectionCommisionSheetPage.getVehicleRegisterDate() != null && !detectionCommisionSheetPage.getVehicleRegisterDate().equals("")) {
				newCarCertificateSheet.setVehicleRegisterDate(detectionCommisionSheetPage.getVehicleRegisterDate());
			}
			// 设置汽车的发证日期
			if (detectionCommisionSheetPage.getVechileIssueCertTime() != null && !detectionCommisionSheetPage.getVechileIssueCertTime().equals("")) {
				newCarCertificateSheet.setVehicleIssueCertDate(detectionCommisionSheetPage.getVechileIssueCertTime());
			}
			// 产生报告编号
			String reportNumber = null;
			String year = (Calendar.getInstance().get(Calendar.YEAR)%100)+"";
			
			HttpSession session=ServletActionContext.getRequest().getSession(true);
			Integer stationId = (Integer) session.getAttribute("stationId");
			String stationName = (String) session.getAttribute("stationName");
			String userName = (String)session.getAttribute("userName");

			newCarCertificateSheet.setFillStationName(stationName);
			newCarCertificateSheet.setFillUserName(userName);
			
			String hql = "FROM SysUser AS s WHERE s.userName =:userName ";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			SysUser user = sysUserDao.get(hql, params);
			Integer userId = user.getUserId();
			
			String stationIds = String.format("%03d", stationId);
			String userIds = String.format("%03d", userId);
			
			String shql="FROM NewCarCertificateSheet as e where e.certificateID = "
					+ "(select max(certificateID) from NewCarCertificateSheet as en where en.certificateID like '3402"+year+stationIds+userIds+"%') ";
			NewCarCertificateSheet ncc = newCarCertificateSheetDao.get(shql);
			if(ncc!=null){
				String maxLabelId = ncc.getCertificateID();
				Integer maxLastNum = Integer.parseInt(maxLabelId.substring(maxLabelId.length()-3, maxLabelId.length()));
				Integer newNum = maxLastNum+1;
				String newNums = String.format("%03d", newNum);
				reportNumber = "3402"+year+stationIds+userIds+newNums;
			}else 
				reportNumber = "3402"+year+stationIds+userIds+"001";
			
			newCarCertificateSheet.setCertificateID(reportNumber);
			//0代表未打印
			newCarCertificateSheet.setIsPrint(0);
			//1代表正常状态.0为已注销
			newCarCertificateSheet.setStatus(1);
			newCarCertificateSheetDao.save(newCarCertificateSheet);
		}
	}

	@Override
	public void addDetectionRecord(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		// TODO Auto-generated method stub
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			DetectionCommisionSheet detectionCommisionSheet = new DetectionCommisionSheet();
			BeanUtils.copyProperties(detectionCommisionSheetPage, detectionCommisionSheet, new String[] { "detectionTime", "vehicleRegisterDate", "validatePeriod", "vechileIssueCertTime" });
			// 设置登记时间
			if (detectionCommisionSheetPage.getVehicleRegisterDate() != null && !detectionCommisionSheetPage.getVehicleRegisterDate().equals("")) {
				detectionCommisionSheet.setVehicleRegisterDate(Timestamp.valueOf(detectionCommisionSheetPage.getVehicleRegisterDate() + " 00:00:00"));
			}
			// 设置汽车的发证日期
			if (detectionCommisionSheetPage.getVechileIssueCertTime() != null && !detectionCommisionSheetPage.getVechileIssueCertTime().equals("")) {
				detectionCommisionSheet.setVechileIssueCertTime(ChangeTimeFormat.getInstance().strToTimeStamp(detectionCommisionSheetPage.getVechileIssueCertTime()));
			}
			
			// 设置委托单状态的初始状态为4（新车记录）
			detectionCommisionSheet.setCommisionSheetStatus(4);
			
			detectionCommisionSheetDao.save(detectionCommisionSheet);
		}
	}
	@Override
	public Json addVehicletype(VehicleTypeInfoPage vehicleTypeInfoPage) {
		Json json = new Json();
		VehicleTypeInfo vehicletypeInfo = new VehicleTypeInfo();
		BeanUtils.copyProperties(vehicleTypeInfoPage, vehicletypeInfo,new String[]{"CLXH","FDJXH"});
		vehicletypeInfo.setCLXH(vehicleTypeInfoPage.getCLXH().trim());
		vehicletypeInfo.setFDJXH(vehicleTypeInfoPage.getFDJXH().trim());
		vehicleTypeDao.save(vehicletypeInfo);
		json.setSuccess(true);
		json.setMsg("添加成功");
		
		return json;
	}
	
	/**
	 * 获取车型信息（2017-12-19添加）
	 */
	@Override
	public DataGrid getAllVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage){
		DataGrid dg = new DataGrid();
		if (vehicleTypeInfoPage != null) {
			try {
				vehicleTypeInfoPage = (VehicleTypeInfoPage) TrimString.getInstance().trimObjectString(vehicleTypeInfoPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql = "from VehicleTypeInfo as vt where 1=1 ";
			Map<String, Object> tMap = new HashMap<String, Object>();
			if (vehicleTypeInfoPage.getID() != null) {
				hql += " and vt.ID=:ID ";
				tMap.put("ID", vehicleTypeInfoPage.getID());
			}
			if (vehicleTypeInfoPage.getCLXH() != null && !vehicleTypeInfoPage.getCLXH().equals("")) {				
				hql += " and vt.CLXH =:CLXH ";				
				tMap.put("CLXH", vehicleTypeInfoPage.getCLXH());
			}
			if (vehicleTypeInfoPage.getCLMC() != null && !vehicleTypeInfoPage.getCLMC().equals("")) {
				hql += " and vt.CLMC like :CLMC";
				tMap.put("CLMC", "%" + vehicleTypeInfoPage.getCLMC() + "%");
			}
			if (vehicleTypeInfoPage.getFDJXH() != null && !vehicleTypeInfoPage.getFDJXH().equals("")) {
				hql += " and vt.FDJXH =:FDJXH ";
				tMap.put("FDJXH", vehicleTypeInfoPage.getFDJXH());
			}
			if (vehicleTypeInfoPage.getFDJSCC() != null && !vehicleTypeInfoPage.getFDJSCC().equals("")) {
				hql += " and vt.FDJSCC like :FDJSCC ";
				tMap.put("FDJSCC", "%" + vehicleTypeInfoPage.getFDJSCC() + "%");
			}
			if (vehicleTypeInfoPage.getMANUF() != null && !vehicleTypeInfoPage.getMANUF().equals("")) {
				hql += " and vt.MANUF like :MANUF ";
				tMap.put("MANUF", "%" + vehicleTypeInfoPage.getMANUF() + "%");
			}
			if (vehicleTypeInfoPage.getBeginTime() != null && !vehicleTypeInfoPage.getBeginTime().equals("")) {
				hql += " and vt.FILENAME>=:beginTime ";				
				tMap.put("beginTime", vehicleTypeInfoPage.getBeginTime());
			}
			if (vehicleTypeInfoPage.getEndTime() != null && !vehicleTypeInfoPage.getEndTime().equals("")) {
				hql += " and vt.FILENAME<=:endTime";				
				tMap.put("endTime", vehicleTypeInfoPage.getEndTime());
			}			
			if (vehicleTypeInfoPage.getPF() != null && !vehicleTypeInfoPage.getPF().equals("0")) {
				hql += " and vt.PF=:PF ";
				tMap.put("PF", vehicleTypeInfoPage.getPF());
			}
			if (vehicleTypeInfoPage.getCLLB() != null && !vehicleTypeInfoPage.getCLLB().equals("")) {
				hql += " and vt.CLLB =:CLLB ";
				tMap.put("CLLB", vehicleTypeInfoPage.getCLLB());
			}
			if (vehicleTypeInfoPage.getCLSB() != null && !vehicleTypeInfoPage.getCLSB().equals("")) {
				hql += " and vt.CLSB like :CLSB ";
				tMap.put("CLSB", "%" + vehicleTypeInfoPage.getCLSB() + "%");
			}
						

			dg.setTotal(vehicleTypeDao.count("select count(ID) " + hql, tMap));
			hql += " order by vt.ID desc ";
			List<VehicleTypeInfo> list = vehicleTypeDao.find(hql, tMap, vehicleTypeInfoPage.getPage(), vehicleTypeInfoPage.getRows());
			List<VehicleTypeInfoPage> resList = new ArrayList<VehicleTypeInfoPage>();
			for (VehicleTypeInfo vehicletypeInfo : list) {
				VehicleTypeInfoPage vct = new VehicleTypeInfoPage();
				BeanUtils.copyProperties(vehicletypeInfo, vct, new String[] {});
				
				resList.add(vct);
			}
			dg.setRows(resList);
		}
		return dg;
	}
	
	/**
	 * 修改车型信息（2017-12-19添加）
	 */
	@Override
	public Json modifyVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage){
		Json res = new Json();
		res.setMsg("没有传入修改数据");
		if (vehicleTypeInfoPage != null) {
			try {
				vehicleTypeInfoPage = (VehicleTypeInfoPage) TrimString.getInstance().trimObjectString(vehicleTypeInfoPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql = "from VehicleTypeInfo as dcs where dcs.ID=:ID";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("ID", vehicleTypeInfoPage.getID());
			VehicleTypeInfo vehicletypeInfo = vehicleTypeDao.find(hql, tMap).get(0);
			BeanUtils.copyProperties(vehicleTypeInfoPage, vehicletypeInfo, new String[] {});			
			try {
				vehicleTypeDao.update(vehicletypeInfo);
				res.setSuccess(true);
				res.setMsg("修改车型信息成功");
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("修改车型信息失败");
				return res;
			}
		}

		return res;
	}
	
	
	/**
	 * 删除车型信息（2017-12-20添加）
	 */
	@Override
	public Json deleteVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage) {
		Json j = new Json();
		try {
			if (vehicleTypeInfoPage.getIds() != null) {
				for (String id : vehicleTypeInfoPage.getIds().split(",")) {
					if (id != null && !id.trim().equals("")) {
						VehicleTypeInfo vehicleTypeInfo = vehicleTypeDao.get(
								VehicleTypeInfo.class, Integer.valueOf(id));
						if (vehicleTypeInfo != null) {
							vehicleTypeDao.delete(vehicleTypeInfo);
						} else {
							j.setSuccess(false);
							j.setMsg("未找到对应车型记录！");
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
	
}
