package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.CalibrationRecordPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;

public interface IDetectionLineCalibrationService {
	
	/**
	 * 添加检测线标定记录
	 * @param calibrationRecordPage
	 * @return
	 */
	public Json addCalibrationRecord(CalibrationRecordPage calibrationRecordPage);
   /**
    * 删除检测线标定记录
    * @param calibrationRecordPage
    * @return
    */
	public Json deleteCalibrationRecord(CalibrationRecordPage calibrationRecordPage);
    /**
     * 返回所有的检测线标定记录，或者返回符合查询条件的检测线标定记录
     * @param calibrationRecordPage
     * @return
     */
	public DataGrid getAllCalibrationRecord(CalibrationRecordPage calibrationRecordPage);
	
	/**
	 * 修改检测线的标定记录  
	 * @param calibrationRecordPage
	 * @return
	 */
	public Json  modifyCalibrationRecord(CalibrationRecordPage calibrationRecordPage);
	/**
	 * 注销检测线的记录
	 * @param calibrationRecordPage
	 * @return
	 */
	public Json cancelCalibrationRecord(CalibrationRecordPage calibrationRecordPage);
   /**
    * 导出检测线的标定记录
    */
	public Json exportLineCarlibration(CalibrationRecordPage calibrationRecordPage);
}
