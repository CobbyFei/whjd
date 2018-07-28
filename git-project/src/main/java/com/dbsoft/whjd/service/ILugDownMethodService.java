package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LugDownMethodPage;

public interface ILugDownMethodService {

	DataGrid getAllDetections(LugDownMethodPage methodPage);

	Json updateMethod(LugDownMethodPage methodPage) throws Exception;

	Json batchConclusion(LugDownMethodPage methodPage);

	LugDownMethodPage getDetectionById(Integer id);

	Json beginDetection(LugDownMethodPage methodPage);

}
