package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TrainingRecordPage;


public interface ITrainingRecordService {
	
	/**
	 *添加培训 与考核记录
	 * @param trainingRecordPage
	 * @return
	 */
	
	public Json addTrainingRecord(TrainingRecordPage trainingRecordPage);
	
	/**
	 * 查找所有符合条件的记录并返回datagrid
	 * @param trainingRecordPage
	 * @return
	 */

	public DataGrid findTrainingRecords(TrainingRecordPage trainingRecordPage);
	
	/**
	 * 更改培训或考核记录
	 * @param traininigRecordPage
	 * @return
	 */
	
	public Json updateTrainingRecord(TrainingRecordPage trainingRecordPage);
	
	
	/**
	 * 更改记录状态
	 * @param trainingRecordPage
	 * @return
	 */
	public Json changeTrainingRecordStatus(TrainingRecordPage trainingRecordPage);
	
	
}
