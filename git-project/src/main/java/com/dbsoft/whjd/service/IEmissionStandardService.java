package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.VehicleTypeInfoPage;

public interface IEmissionStandardService {
	Json judgeAdmit(VehicleTypeInfoPage vehicleTypePage);

	/*
	 * 根据生产日期，车辆型号，发动机型号，查询该车辆的排放标准
	 */
	String getPF(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
	 * 根据总质量，燃油类型，用途，查询车辆准入标准
	 */
	String getZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage);
	/*
	 * 根据车辆型号  查询摩托车准入标准
	 */
	String getMotorZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
	 * 增加新车准入记录
	 */
	void addNewCarAdmitInfo(DetectionCommisionSheetPage detectionCommisionSheetPage);

	void addDetectionRecord(DetectionCommisionSheetPage detectionCommisionSheetPage);

	Json addVehicletype(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
     * 获取车型信息（2017-12-19添加）
     */
	DataGrid getAllVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
	 * 修改车型信息（2017-12-19添加）
	 */
	Json modifyVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
	 * 删除车型信息（2017-12-20添加）
	 */
	Json deleteVehicleType(VehicleTypeInfoPage vehicleTypeInfoPage);

	/*
	 * 获取在用车准入条件（2018-01-02添加）2018-2-3注释，2018-2-23重新添加
	 */
	String getUsingCarZRBZ(VehicleTypeInfoPage vehicleTypeInfoPage);
	
}
