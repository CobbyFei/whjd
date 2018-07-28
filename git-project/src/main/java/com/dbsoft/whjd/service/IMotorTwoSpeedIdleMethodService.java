package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MotorTwoSpeedIdleMethodPage;
import com.dbsoft.whjd.pageModel.TwoSpeedIdleMethodPage;

public interface IMotorTwoSpeedIdleMethodService {
	 public DataGrid getMotorTwoSpeedIdleMethod(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage);
	 public Json updateMotorTwoSpeedIdleMethod(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage);
	 public Json batchConclusion(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage);
	 public MotorTwoSpeedIdleMethodPage getDetectionById(Integer id);
	 public Json beginDetection(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage);
}
