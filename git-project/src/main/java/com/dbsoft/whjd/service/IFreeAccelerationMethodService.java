package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IFreeAccelerationMethodService {

	Json updateMethod(FreeAccelerationMethodPage methodPage) throws Exception;

	DataGrid getAllDetections(FreeAccelerationMethodPage methodPage);

	FreeAccelerationMethodPage getDetectionById(Integer id);

	Json batchConclusion(FreeAccelerationMethodPage methodPage);

	Json beginDetection(FreeAccelerationMethodPage methodPage);

}
