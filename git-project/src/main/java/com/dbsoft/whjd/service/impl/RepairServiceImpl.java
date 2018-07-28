package com.dbsoft.whjd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.Menu;
import com.dbsoft.whjd.service.IRepairService;

@Service("repairService")
public class RepairServiceImpl implements IRepairService {

	private IBaseDao<Menu> menuDao;

	public IBaseDao<Menu> getMenuDao() {
		return menuDao;
	}

	@Resource(name = "baseDao")
	public void seMenuDao(IBaseDao<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public void repair() {
		// 修复菜单
		repairMenu();

	}

	/**
	 * iconCls: 图标所对应的url text: 该功能的说明 url: 该功能所对应的路径 admin: 是否是管理员权限，1.管理员
	 * 0.普通用户 如果该功能是普通用户的话，设置它为0，管理员不需要设置它，直接让它为空 Menu: 父节点
	 */
	private void repairMenu() {
		// 权限菜单
		Menu rolegl = new Menu();
		rolegl.setId("r");
		rolegl.setIconCls(null);
		rolegl.setText("权限管理");
		rolegl.setUrl(null);
		rolegl.setAdmin(null);
		rolegl.setMenu(null);
		rolegl.setSeq(0);
		menuDao.saveOrUpdate(rolegl);

		Menu roleadd = new Menu();
		roleadd.setId("r1");
		roleadd.setIconCls(null);
		roleadd.setText("添加角色");
		roleadd.setUrl("/jsp/RoleAuthManage/addNewRole.jsp");
		roleadd.setAdmin(null);
		roleadd.setMenu(rolegl);
		roleadd.setSeq(1);
		menuDao.saveOrUpdate(roleadd);

		Menu rolemng = new Menu();
		rolemng.setId("r2");
		rolemng.setIconCls(null);
		rolemng.setText("角色管理");
		rolemng.setUrl("/jsp/RoleAuthManage/roleManage.jsp");
		rolemng.setAdmin(null);
		rolemng.setMenu(rolegl);
		rolemng.setSeq(2);
		menuDao.saveOrUpdate(rolemng);

		Menu adduserrole = new Menu();
		adduserrole.setId("r3");
		adduserrole.setIconCls(null);
		adduserrole.setText("添加用户角色");
		adduserrole.setUrl("/jsp/RoleAuthManage/addUserRole.jsp");
		adduserrole.setAdmin(null);
		adduserrole.setMenu(rolegl);
		adduserrole.setSeq(3);
		menuDao.saveOrUpdate(adduserrole);

		Menu userrolemng = new Menu();
		userrolemng.setId("r4");
		userrolemng.setIconCls(null);
		userrolemng.setText("用户角色管理");
		userrolemng.setUrl("/jsp/RoleAuthManage/userRoleManage.jsp");
		userrolemng.setAdmin(null);
		userrolemng.setMenu(rolegl);
		userrolemng.setSeq(4);
		menuDao.saveOrUpdate(userrolemng);

		Menu roleprimng = new Menu();
		roleprimng.setId("r5");
		roleprimng.setIconCls(null);
		roleprimng.setText("角色资源管理");
		roleprimng.setUrl("/jsp/RoleAuthManage/rolePrivilegeMng.jsp");
		roleprimng.setAdmin(null);
		roleprimng.setMenu(rolegl);
		roleprimng.setSeq(5);
		menuDao.saveOrUpdate(roleprimng);

		// 用户管理菜单
		Menu usergl = new Menu();
		usergl.setId("u");
		usergl.setIconCls(null);
		usergl.setText("用户管理");
		usergl.setUrl(null);
		usergl.setAdmin(null);
		usergl.setMenu(null);
		usergl.setSeq(1);
		menuDao.saveOrUpdate(usergl);

		Menu useradd = new Menu();
		useradd.setId("u1");
		useradd.setIconCls(null);
		useradd.setText("新增人员信息");
		useradd.setUrl("/jsp/UserManage/addNewUser.jsp");
		useradd.setAdmin(null);
		useradd.setMenu(usergl);
		useradd.setSeq(1);
		menuDao.saveOrUpdate(useradd);

		Menu userManage = new Menu();
		userManage.setId("u2");
		userManage.setIconCls(null);
		userManage.setText("人员信息管理");
		userManage.setUrl("/jsp/UserManage/sysUserManage.jsp");
		userManage.setAdmin(null);
		userManage.setMenu(usergl);
		userManage.setSeq(2);
		menuDao.saveOrUpdate(userManage);

		Menu changePassword = new Menu();
		changePassword.setId("u3");
		changePassword.setIconCls(null);
		changePassword.setText("更改口令");
		changePassword.setUrl("/jsp/UserManage/changePassword.jsp");
		changePassword.setAdmin(null);
		changePassword.setMenu(usergl);
		changePassword.setSeq(3);
		menuDao.saveOrUpdate(changePassword);

		Menu resetPassword = new Menu();
		resetPassword.setId("u4");
		resetPassword.setIconCls(null);
		resetPassword.setText("重置口令");
		resetPassword.setUrl("/jsp/UserManage/resetPassword.jsp");
		resetPassword.setAdmin(null);
		resetPassword.setMenu(usergl);
		resetPassword.setSeq(4);
		menuDao.saveOrUpdate(resetPassword);

		// 检测站管理菜单
		Menu isgl = new Menu();
		isgl.setId("i");
		isgl.setIconCls(null);
		isgl.setText("标志核发点管理");
		isgl.setUrl(null);
		isgl.setAdmin(null);
		isgl.setMenu(null);
		isgl.setSeq(2);
		menuDao.saveOrUpdate(isgl);

		Menu isadd = new Menu();
		isadd.setId("i1");
		isadd.setIconCls(null);
		isadd.setText("新增核发点信息");
		isadd.setUrl("/jsp/InspectionStationManage/addInspectionStation.jsp");
		isadd.setAdmin(null);
		isadd.setMenu(isgl);
		isadd.setSeq(1);
		menuDao.saveOrUpdate(isadd);

		Menu ismanage = new Menu();
		ismanage.setId("i2");
		ismanage.setIconCls(null);
		ismanage.setText("核发点信息管理");
		ismanage.setUrl("/jsp/InspectionStationManage/inspectionStationManage.jsp");
		ismanage.setAdmin(null);
		ismanage.setMenu(isgl);
		ismanage.setSeq(2);
		menuDao.saveOrUpdate(ismanage);

		// 检测线管理菜单
		Menu dlgl = new Menu();
		dlgl.setId("p");
		dlgl.setIconCls(null);
		dlgl.setText("检测线管理");
		dlgl.setUrl(null);
		dlgl.setAdmin(null);
		dlgl.setMenu(null);
		dlgl.setSeq(3);
		menuDao.saveOrUpdate(dlgl);

		Menu dladd = new Menu();
		dladd.setId("p1");
		dladd.setIconCls(null);
		dladd.setText("添加检测线信息");
		dladd.setUrl("/jsp/DetectionLineManage/addDetectionLine.jsp");
		dladd.setAdmin(null);
		dladd.setMenu(dlgl);
		dladd.setSeq(1);
		menuDao.saveOrUpdate(dladd);

		Menu dlmanage = new Menu();
		dlmanage.setId("p2");
		dlmanage.setIconCls(null);
		dlmanage.setText("检测线信息管理");
		dlmanage.setUrl("/jsp/DetectionLineManage/manageDetectionline.jsp");
		dlmanage.setAdmin(null);
		dlmanage.setMenu(dlgl);
		dlmanage.setSeq(2);
		menuDao.saveOrUpdate(dlmanage);

		// 设备采购记录管理
		Menu dpdl = new Menu();
		dpdl.setId("d");
		dpdl.setIconCls(null);
		dpdl.setText("设备采购记录管理");
		dpdl.setUrl(null);
		dpdl.setAdmin(null);
		dpdl.setMenu(null);
		dpdl.setSeq(4);
		menuDao.saveOrUpdate(dpdl);

		Menu dpadd = new Menu();
		dpadd.setId("d1");
		dpadd.setIconCls(null);
		dpadd.setText("添加设备采购记录");
		dpadd.setUrl("/jsp/DevicePurchaseRecordManage/addDevicePurchaseRecord.jsp");
		dpadd.setAdmin(null);
		dpadd.setMenu(dpdl);
		dpadd.setSeq(1);
		menuDao.saveOrUpdate(dpadd);

		Menu dpmanage = new Menu();
		dpmanage.setId("d2");
		dpmanage.setIconCls(null);
		dpmanage.setText("设备采购记录管理");
		dpmanage.setUrl("/jsp/DevicePurchaseRecordManage/manageDevicePurchaseRecord.jsp");
		dpmanage.setAdmin(null);
		dpmanage.setMenu(dpdl);
		dpmanage.setSeq(2);
		menuDao.saveOrUpdate(dpmanage);

		// 标准物质的采购记录
		Menu smgl = new Menu();
		smgl.setId("s");
		smgl.setIconCls(null);
		smgl.setText("标准物质采购记录");
		smgl.setUrl(null);
		smgl.setAdmin(null);
		smgl.setMenu(null);
		smgl.setSeq(5);
		menuDao.saveOrUpdate(smgl);

		Menu smadd = new Menu();
		smadd.setId("s1");
		smadd.setIconCls(null);
		smadd.setText("添加采购记录");
		smadd.setUrl("/jsp/referenceMaterialsRecord/addReferenceMaterialsRecord.jsp");
		smadd.setAdmin(null);
		smadd.setMenu(smgl);
		smadd.setSeq(1);
		menuDao.saveOrUpdate(smadd);

		Menu smManage = new Menu();
		smManage.setId("s2");
		smManage.setIconCls(null);
		smManage.setText("管理采购记录");
		smManage.setUrl("/jsp/referenceMaterialsRecord/referenceMaterialsRecordManage.jsp");
		smManage.setAdmin(null);
		smManage.setMenu(smgl);
		smManage.setSeq(2);
		menuDao.saveOrUpdate(smManage);

		// 检测设备信息管理
		Menu ddgl = new Menu();
		ddgl.setId("dd");
		ddgl.setIconCls(null);
		ddgl.setText("检测设备信息管理");
		ddgl.setUrl(null);
		ddgl.setAdmin(null);
		ddgl.setMenu(null);
		ddgl.setSeq(6);
		menuDao.saveOrUpdate(ddgl);

		Menu ddAdd = new Menu();
		ddAdd.setId("dd1");
		ddAdd.setIconCls(null);
		ddAdd.setText("新增设备信息");
		ddAdd.setUrl("/jsp/deviceInfo/addDeviceInfo.jsp");
		ddAdd.setAdmin(null);
		ddAdd.setMenu(ddgl);
		ddAdd.setSeq(1);
		menuDao.saveOrUpdate(ddAdd);

		Menu ddManage = new Menu();
		ddManage.setId("dd2");
		ddManage.setIconCls(null);
		ddManage.setText("管理设备信息");
		ddManage.setUrl("/jsp/deviceInfo/manageDeviceInfo.jsp");
		ddManage.setAdmin(null);
		ddManage.setMenu(ddgl);
		ddManage.setSeq(2);
		menuDao.saveOrUpdate(ddManage);

		// 检测设备维护记录管理
		Menu dmgl = new Menu();
		dmgl.setId("dm");
		dmgl.setIconCls(null);
		dmgl.setText("检测设备维护记录管理");
		dmgl.setUrl(null);
		dmgl.setAdmin(null);
		dmgl.setMenu(null);
		dmgl.setSeq(7);
		menuDao.saveOrUpdate(dmgl);

		Menu dmAdd = new Menu();
		dmAdd.setId("dm1");
		dmAdd.setIconCls(null);
		dmAdd.setText("新增维护记录");
		dmAdd.setUrl("/jsp/maintainceRecord/addMaintainceRecord.jsp");
		dmAdd.setAdmin(null);
		dmAdd.setMenu(dmgl);
		dmAdd.setSeq(1);
		menuDao.saveOrUpdate(dmAdd);

		Menu dmManage = new Menu();
		dmManage.setId("dm2");
		dmManage.setIconCls(null);
		dmManage.setText("管理维护记录");
		dmManage.setUrl("/jsp/maintainceRecord/manageMaintainceRecord.jsp");
		dmManage.setAdmin(null);
		dmManage.setMenu(dmgl);
		dmManage.setSeq(2);
		menuDao.saveOrUpdate(dmManage);

		// 环保标志领用管理
		Menu collar = new Menu();
		collar.setId("cl");
		collar.setIconCls(null);
		collar.setText("环保标志领用管理");
		collar.setUrl(null);
		collar.setAdmin(null);
		collar.setMenu(null);
		collar.setSeq(10);
		menuDao.saveOrUpdate(collar);

		Menu collar1 = new Menu();
		collar1.setId("cl1");
		collar1.setIconCls(null);
		collar1.setText("新增环保标志领用记录");
		collar1.setUrl("/jsp/EnvironmentalLabelCollarManage/addEnvironmentalLabelCollar.jsp");
		collar1.setAdmin(null);
		collar1.setMenu(collar);
		collar1.setSeq(1);
		menuDao.saveOrUpdate(collar1);

		Menu collar2 = new Menu();
		collar2.setId("cl2");
		collar2.setIconCls(null);
		collar2.setText("管理环保标志领用记录");
		collar2.setUrl("/jsp/EnvironmentalLabelCollarManage/environmentalLabelCollarManage.jsp");
		collar2.setAdmin(null);
		collar2.setMenu(collar);
		collar2.setSeq(2);
		menuDao.saveOrUpdate(collar2);

		Menu collar3 = new Menu();
		collar3.setId("cl3");
		collar3.setIconCls(null);
		collar3.setText("新增市局环保标志领用记录");
		collar3.setUrl("/jsp/EnvironmentalLabelCollarManage/addAgencyCollarRecord.jsp");
		collar3.setAdmin(null);
		collar3.setMenu(collar);
		collar3.setSeq(3);
		menuDao.saveOrUpdate(collar3);

		Menu collar4 = new Menu();
		collar4.setId("cl4");
		collar4.setIconCls(null);
		collar4.setText("管理市局环保标志领用记录");
		collar4.setUrl("/jsp/EnvironmentalLabelCollarManage/agencyEnvironmentalLabelCollarManage.jsp");
		collar4.setAdmin(null);
		collar4.setMenu(collar);
		collar4.setSeq(4);
		menuDao.saveOrUpdate(collar4);

		// 检测线的标定记录菜单
		Menu dlbd = new Menu();
		dlbd.setId("dll");
		dlbd.setIconCls(null);
		dlbd.setText("检测线标定记录管理");
		dlbd.setUrl(null);
		dlbd.setAdmin(null);
		dlbd.setMenu(null);
		dlbd.setSeq(11);
		menuDao.saveOrUpdate(dlbd);

		Menu add_dlbd = new Menu();
		add_dlbd.setId("dl1");
		add_dlbd.setIconCls(null);
		add_dlbd.setText("新增检测线标定记录");
		add_dlbd.setUrl("/jsp/DetectionLineCalibrationManage/addDetectionLineCalibrationRecord.jsp");
		add_dlbd.setAdmin(null);
		add_dlbd.setMenu(dlbd);
		add_dlbd.setSeq(1);
		menuDao.saveOrUpdate(add_dlbd);

		Menu dlbd_manage = new Menu();
		dlbd_manage.setId("dl2");
		dlbd_manage.setIconCls(null);
		dlbd_manage.setText("管理检测线标定记录");
		dlbd_manage.setUrl("/jsp/DetectionLineCalibrationManage/DetectionLineCalibrationManage.jsp");
		dlbd_manage.setAdmin(null);
		dlbd_manage.setMenu(dlbd);
		dlbd_manage.setSeq(2);
		menuDao.saveOrUpdate(dlbd_manage);

		// 检测方法限值参照管理
		Menu lvref = new Menu();
		lvref.setId("lvref");
		lvref.setIconCls(null);
		lvref.setText("检测方法限值参照管理");
		lvref.setUrl(null);
		lvref.setAdmin(null);
		lvref.setMenu(null);
		lvref.setSeq(12);
		menuDao.saveOrUpdate(lvref);

		Menu add_zjref = new Menu();
		add_zjref.setId("zjref");
		add_zjref.setIconCls(null);
		add_zjref.setText("修改自由加速法限值");
		add_zjref.setUrl("/jsp/limitValueReference/manageFreeAccelerationLimitValue.jsp");
		add_zjref.setAdmin(null);
		add_zjref.setMenu(lvref);
		add_zjref.setSeq(1);
		menuDao.saveOrUpdate(add_zjref);

		Menu add_wtref = new Menu();
		add_wtref.setId("wtref");
		add_wtref.setIconCls(null);
		add_wtref.setText("修改稳态工况法限值");
		add_wtref.setUrl("/jsp/limitValueReference/manageSteadyStateLimitValue.jsp");
		add_wtref.setAdmin(null);
		add_wtref.setMenu(lvref);
		add_wtref.setSeq(2);
		menuDao.saveOrUpdate(add_wtref);

		Menu add_jzref = new Menu();
		add_jzref.setId("jzref");
		add_jzref.setIconCls(null);
		add_jzref.setText("修改加载减速法限值");
		add_jzref.setUrl("/jsp/limitValueReference/manageLugDownLimitValue.jsp");
		add_jzref.setAdmin(null);
		add_jzref.setMenu(lvref);
		add_jzref.setSeq(3);
		menuDao.saveOrUpdate(add_jzref);

		Menu add_sdsf = new Menu();
		add_sdsf.setId("sdsf");
		add_sdsf.setIconCls(null);
		add_sdsf.setText("修改双怠速法限值");
		add_sdsf.setUrl("/jsp/limitValueReference/manageTwoSpeedIdleLimitValue.jsp");
		add_sdsf.setAdmin(null);
		add_sdsf.setMenu(lvref);
		add_sdsf.setSeq(4);
		menuDao.saveOrUpdate(add_sdsf);

		Menu add_msdsf = new Menu();
		add_msdsf.setId("msdsf");
		add_msdsf.setIconCls(null);
		add_msdsf.setText("修改摩托车双怠速法限值");
		add_msdsf.setUrl("/jsp/limitValueReference/manageMotorTwoSpeedIdleLimitValue.jsp");
		add_msdsf.setAdmin(null);
		add_msdsf.setMenu(lvref);
		add_msdsf.setSeq(5);
		menuDao.saveOrUpdate(add_msdsf);

		// 检测方法的参照的信息管理
		Menu dmref = new Menu();
		dmref.setId("dmref");
		dmref.setIconCls(null);
		dmref.setText("检测方法参照信息管理");
		dmref.setUrl(null);
		dmref.setAdmin(null);
		dmref.setMenu(null);
		dmref.setSeq(12);
		menuDao.saveOrUpdate(dmref);

		Menu add_dmref = new Menu();
		add_dmref.setId("dmref1");
		add_dmref.setIconCls(null);
		add_dmref.setText("新增检测方法参照记录");
		add_dmref.setUrl("/jsp/DetectionMethodReferenceManage/addDetectionMethodReferenceRecord.jsp");
		add_dmref.setAdmin(null);
		add_dmref.setMenu(dmref);
		add_dmref.setSeq(1);
		menuDao.saveOrUpdate(add_dmref);

		Menu dmref_manage = new Menu();
		dmref_manage.setId("dmref2");
		dmref_manage.setIconCls(null);
		dmref_manage.setText("检测方法参照信息管理");
		dmref_manage.setUrl("/jsp/DetectionMethodReferenceManage/manageDetectionMethodReference.jsp");
		dmref_manage.setAdmin(null);
		dmref_manage.setMenu(dmref);
		dmref_manage.setSeq(2);
		menuDao.saveOrUpdate(dmref_manage);

		// 车辆限行黑名单管理
		Menu vlgl = new Menu();
		vlgl.setId("vlgl");
		vlgl.setIconCls(null);
		vlgl.setText("车辆限行管理");
		vlgl.setUrl(null);
		vlgl.setAdmin(null);
		vlgl.setMenu(null);
		vlgl.setSeq(13);
		menuDao.saveOrUpdate(vlgl);

		Menu add_vlgl = new Menu();
		add_vlgl.setId("vlgl1");
		add_vlgl.setIconCls(null);
		add_vlgl.setText("添加车辆限行照记录");
		add_vlgl.setUrl("/jsp/VehicleLimitManage/addVehicleLimitRecord.jsp");
		add_vlgl.setAdmin(null);
		add_vlgl.setMenu(vlgl);
		add_vlgl.setSeq(1);
		menuDao.saveOrUpdate(add_vlgl);

		Menu vlgl_manage = new Menu();
		vlgl_manage.setId("vlgl2");
		vlgl_manage.setIconCls(null);
		vlgl_manage.setText("车辆限行信息管理");
		vlgl_manage.setUrl("/jsp/VehicleLimitManage/VehicleLimitRecordManage.jsp");
		vlgl_manage.setAdmin(null);
		vlgl_manage.setMenu(vlgl);
		vlgl_manage.setSeq(2);
		menuDao.saveOrUpdate(vlgl_manage);

		// 培训与考核记录管理
		Menu trdm = new Menu();
		trdm.setId("trdm");
		trdm.setIconCls(null);
		trdm.setText("培训与考核记录管理");
		trdm.setUrl(null);
		trdm.setAdmin(null);
		trdm.setMenu(null);
		trdm.setSeq(13);
		menuDao.saveOrUpdate(trdm);

		Menu add_trdm = new Menu();
		add_trdm.setId("trdm1");
		add_trdm.setIconCls(null);
		add_trdm.setText("添加培训与考核记录");
		add_trdm.setUrl("/jsp/TrainingRecordManage/addTrainingRecord.jsp");

		add_trdm.setAdmin(null);
		add_trdm.setMenu(trdm);
		add_trdm.setSeq(1);
		menuDao.saveOrUpdate(add_trdm);

		Menu trdm_manage = new Menu();
		trdm_manage.setId("trdm2");
		trdm_manage.setIconCls(null);
		trdm_manage.setText("培训与考核记录管理");
		trdm_manage.setUrl("/jsp/TrainingRecordManage/trainingRecordManage.jsp");

		trdm_manage.setAdmin(null);
		trdm_manage.setMenu(trdm);
		trdm_manage.setSeq(2);
		menuDao.saveOrUpdate(trdm_manage);

		// 检测委托单管理
		Menu dcsMenu = new Menu();
		dcsMenu.setId("dcs");
		dcsMenu.setIconCls(null);
		dcsMenu.setText("检测委托单管理");
		dcsMenu.setUrl(null);
		dcsMenu.setAdmin(null);
		dcsMenu.setMenu(null);
		dcsMenu.setSeq(14);
		menuDao.saveOrUpdate(dcsMenu);

		Menu add_dcs_menu = new Menu();
		add_dcs_menu.setId("dcs1");
		add_dcs_menu.setIconCls(null);
		add_dcs_menu.setText("新建检测委托单");
		add_dcs_menu.setUrl("/jsp/DetectionCommisionSheetManage/addDetectionCommistionSheet.jsp");

		add_dcs_menu.setAdmin(null);
		add_dcs_menu.setMenu(dcsMenu);
		add_dcs_menu.setSeq(1);
		menuDao.saveOrUpdate(add_dcs_menu);

		Menu dcs_manage = new Menu();
		dcs_manage.setId("dcs2");
		dcs_manage.setIconCls(null);
		dcs_manage.setText("委托单信息管理");
		dcs_manage.setUrl("/jsp/DetectionCommisionSheetManage/CommisionSheetManage.jsp");

		dcs_manage.setAdmin(null);
		dcs_manage.setMenu(dcsMenu);
		dcs_manage.setSeq(2);
		menuDao.saveOrUpdate(dcs_manage);
		// 检测方法数据管理
		Menu detectionMethod = new Menu();
		detectionMethod.setId("detectionMethod");
		detectionMethod.setIconCls(null);
		detectionMethod.setText("检测方法数据管理");
		detectionMethod.setUrl(null);
		detectionMethod.setAdmin(null);
		detectionMethod.setMenu(null);
		detectionMethod.setSeq(15);
		menuDao.saveOrUpdate(detectionMethod);

		Menu twoSpeedIdleMethod = new Menu();
		twoSpeedIdleMethod.setId("twoSpeedIdleMethod");
		twoSpeedIdleMethod.setIconCls(null);
		twoSpeedIdleMethod.setText("双怠速法检测数据管理");
		twoSpeedIdleMethod.setUrl("/jsp/TwoSpeedIdleMethodManage/twoSpeedIdleMethodManage.jsp");
		twoSpeedIdleMethod.setAdmin(null);
		twoSpeedIdleMethod.setMenu(detectionMethod);
		twoSpeedIdleMethod.setSeq(1);
		menuDao.saveOrUpdate(twoSpeedIdleMethod);

		Menu motorTwoSpeedIdleMethod = new Menu();
		motorTwoSpeedIdleMethod.setId("motorTwoSpeedIdleMethod");
		motorTwoSpeedIdleMethod.setIconCls(null);
		motorTwoSpeedIdleMethod.setText("摩托车双怠速法检测数据管理");
		motorTwoSpeedIdleMethod.setUrl("/jsp/MotorTwoSpeedIdleMethodManage/motorTwoSpeedIdleMethodManage.jsp");
		motorTwoSpeedIdleMethod.setAdmin(null);
		motorTwoSpeedIdleMethod.setMenu(detectionMethod);
		motorTwoSpeedIdleMethod.setSeq(1);
		menuDao.saveOrUpdate(motorTwoSpeedIdleMethod);

		// 标志发放管理
		Menu eLabel = new Menu();
		eLabel.setId("eLabel");
		eLabel.setIconCls(null);
		eLabel.setText("环保检验合格通知单发放管理");
		eLabel.setUrl(null);
		eLabel.setAdmin(null);
		eLabel.setMenu(null);
		eLabel.setSeq(17);
		menuDao.saveOrUpdate(eLabel);

		Menu eLabelManage = new Menu();
		eLabelManage.setId("eLabelManage");
		eLabelManage.setIconCls(null);
		eLabelManage.setText("环保检验合格通知单发放数据管理");
		eLabelManage.setUrl("/jsp/EnvironmentalLabelManage/environmentalLabelManage.jsp");
		eLabelManage.setAdmin(null);
		eLabelManage.setMenu(eLabel);
		eLabelManage.setSeq(1);
		menuDao.saveOrUpdate(eLabelManage);

		Menu freeAccelerationMethod = new Menu();
		freeAccelerationMethod.setId("freeAccelerationMethod");
		freeAccelerationMethod.setIconCls(null);
		freeAccelerationMethod.setText("自由加速法检测数据管理");
		freeAccelerationMethod.setUrl("/jsp/freeAccelerationMethodDetection/manageFreeAccelerationMethodDetection.jsp");
		freeAccelerationMethod.setAdmin(null);
		freeAccelerationMethod.setMenu(detectionMethod);
		freeAccelerationMethod.setSeq(1);
		menuDao.saveOrUpdate(freeAccelerationMethod);

		Menu lugDownMethod = new Menu();
		lugDownMethod.setId("lugDownMethod");
		lugDownMethod.setIconCls(null);
		lugDownMethod.setText("加载减速法检测数据管理");
		lugDownMethod.setUrl("/jsp/lugDownMethodDetection/manageLugDownMethodDetection.jsp");
		lugDownMethod.setAdmin(null);
		lugDownMethod.setMenu(detectionMethod);
		lugDownMethod.setSeq(1);
		menuDao.saveOrUpdate(lugDownMethod);

		Menu steadyStateMethod = new Menu();
		steadyStateMethod.setId("steadyStateMethod");
		steadyStateMethod.setIconCls(null);
		steadyStateMethod.setText("稳态工况法检测数据管理");
		steadyStateMethod.setUrl("/jsp/SteadyStateMethodManage/steadyStateMethodManage.jsp");
		steadyStateMethod.setAdmin(null);
		steadyStateMethod.setMenu(detectionMethod);
		steadyStateMethod.setSeq(1);
		menuDao.saveOrUpdate(steadyStateMethod);

		// 菜单管理
		Menu xtgl = new Menu();
		xtgl.setId("o");
		xtgl.setIconCls(null);
		xtgl.setText("系统管理");
		xtgl.setUrl(null);
		xtgl.setAdmin(null); // 系统管理是管理员的权限
		xtgl.setMenu(null);
		xtgl.setSeq(16);
		menuDao.saveOrUpdate(xtgl);

		Menu cdgl = new Menu();
		cdgl.setId("o1");
		cdgl.setText("菜单管理");
		cdgl.setUrl("/admin/menu.jsp");
		cdgl.setAdmin(null); // 系统管理是管理员的权限
		cdgl.setMenu(xtgl);
		cdgl.setIconCls(null);
		cdgl.setSeq(1);
		menuDao.saveOrUpdate(cdgl);

		// 新车准入
		Menu xczrgl = new Menu();
		xczrgl.setId("zz");
		xczrgl.setIconCls(null);
		xczrgl.setText("车辆准入管理");
		xczrgl.setUrl(null);
		xczrgl.setAdmin(null); // 系统管理是管理员的权限
		xczrgl.setMenu(null);
		xczrgl.setSeq(18);
		menuDao.saveOrUpdate(xczrgl);

		Menu cxpfbz = new Menu();
		cxpfbz.setId("zz1");
		cxpfbz.setText("新车准入");
		cxpfbz.setUrl("/jsp/NewCarAdmit/queryNewCarEmissionStandard.jsp");
		cxpfbz.setAdmin(null); // 系统管理是管理员的权限
		cxpfbz.setMenu(xczrgl);
		cxpfbz.setIconCls(null);
		cxpfbz.setSeq(1);
		menuDao.saveOrUpdate(cxpfbz);
		
		Menu zycpfbzcx = new Menu();
		zycpfbzcx.setId("zz2");
		zycpfbzcx.setText("在用车准入");
		zycpfbzcx.setUrl("/jsp/UsingCarAdmit/queryUsingCarEmissionStandard.jsp");
		zycpfbzcx.setAdmin(null); // 系统管理是管理员的权限
		zycpfbzcx.setMenu(xczrgl);
		zycpfbzcx.setIconCls(null);
		zycpfbzcx.setSeq(2);
		menuDao.saveOrUpdate(zycpfbzcx);
		
		Menu zrzmdy = new Menu();
		zrzmdy.setId("zz3");
		zrzmdy.setText("准入证明打印");
		zrzmdy.setUrl("/jsp/NewCarAdmit/newCarAdmitManage.jsp");
		zrzmdy.setAdmin(null); // 系统管理是管理员的权限
		zrzmdy.setMenu(xczrgl);
		zrzmdy.setIconCls(null);
		zrzmdy.setSeq(3);
		menuDao.saveOrUpdate(zrzmdy);
		
		Menu zrbzgl = new Menu();
		zrbzgl.setId("zz4");
		zrbzgl.setText("新车准入标准管理");
		zrbzgl.setUrl("/jsp/NewCarAdmit/admitStandardManage.jsp");
		zrbzgl.setAdmin(null); // 系统管理是管理员的权限
		zrbzgl.setMenu(xczrgl);
		zrbzgl.setIconCls(null);
		zrbzgl.setSeq(4);
		menuDao.saveOrUpdate(zrbzgl);
		
		Menu xzcxk = new Menu();
		xzcxk.setId("zz5");
		xzcxk.setText("新增车型库");
		xzcxk.setUrl("/jsp/NewCarAdmit/addVehicleType.jsp");
		xzcxk.setAdmin(null); // 系统管理是管理员的权限
		xzcxk.setMenu(xczrgl);
		xzcxk.setIconCls(null);
		xzcxk.setSeq(5);
		menuDao.saveOrUpdate(xzcxk);

	}
}
