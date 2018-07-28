package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SteadyStateMethodPage;

public interface ISteadyStateMethodService {

	
	//修改一条稳态工况法记录
	public Json updateSteadyStateMethodService(SteadyStateMethodPage steadyStateMethodPage);
	
	public DataGrid findCommisionSheetsOfSteadyStateMethod(SteadyStateMethodPage steadyStateMethodPage);
	
	public SteadyStateMethodPage getDetectionById(Integer id);
	
	public Json batchConclusion(SteadyStateMethodPage steadyStateMethodPage);
	
	public Json beginDetection(SteadyStateMethodPage steadyStateMethodPage);
}
