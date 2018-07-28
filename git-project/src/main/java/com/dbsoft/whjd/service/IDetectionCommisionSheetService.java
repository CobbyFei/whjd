package com.dbsoft.whjd.service;

import org.json.JSONException;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.sun.tools.doclets.internal.toolkit.resources.doclets;

public interface IDetectionCommisionSheetService {
    /**
     * 新建检测委托单
     * @param detectionCommisionSheetPage
     * @return
     */
	public Json addDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
    /**
     * 获取委托单信息
     * @param detectionCommisionSheetPage
     * @return
     */
	public DataGrid getAllDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
	
	/**
	 * 车辆预读
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public DataGrid getVehibleInfo(DetectionCommisionSheetPage detectionCommisionSheetPage);
   
	/**
	 * 注销检测委托单
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public Json cancelDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
	/**
	 * 修改检测委托单信息
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public Json modifyDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
	/**
	 * 检测此次是否是核发
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public Json checkIsFirstDetected(DetectionCommisionSheetPage detectionCommisionSheetPage);
	/**
	 * 挡回委托单
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public Json revokeDetectionCommistionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
    /**
     * 调用c#的远程调用接口，此远程接口会返回一个string,这个string的各部分之间用“,”隔开
     * @param detectionCommisionSheetPage
     * @return
     */
	public String distributeLabel(DetectionCommisionSheetPage detectionCommisionSheetPage);
   /**
    * 导出委托单信息
    */
	public Json exportDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage);
    
	/**
	 * 根据委托单编号取出一条记录
	 */
	public DetectionCommisionSheet getDetectionCommisionSheetById(Integer id);
	
	/**
	 * 根据车辆初测日期、车辆型号、发动机型号，查询车辆排放标准
	 */
	public DataGrid getEmissionStandard(DetectionCommisionSheetPage detectionCommisionSheetPage);
	/**
	 * 根据车辆车牌号码和车牌颜色查询车辆基本信息
	 * @param detectionCommisionSheetPage
	 * @return
	 */
	public DataGrid getVehicleInfoByHBTing(DetectionCommisionSheetPage detectionCommisionSheetPage) throws JSONException;
}
