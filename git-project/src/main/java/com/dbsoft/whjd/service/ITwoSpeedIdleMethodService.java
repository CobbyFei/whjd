package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TwoSpeedIdleMethodPage;

public interface ITwoSpeedIdleMethodService {
	 public DataGrid getTwoSpeedIdleMethod(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage);
	 //public List<TwoSpeedIdleMethodPage> getTwoSpeedIdleMethodName(String q);
	// public Json addTwoSpeedIdleMethod(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage);
	 public Json updateTwoSpeedIdleMethod(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage);
	 public Json batchConclusion(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage);
	 public TwoSpeedIdleMethodPage getDetectionById(Integer id);
	 public Json beginDetection(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage);
}
