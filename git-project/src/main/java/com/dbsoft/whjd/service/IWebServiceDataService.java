package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.WebServiceDataInteractionPage;

public interface IWebServiceDataService {
	/**
	 * 插入硬件检测任务
	 * @param webServiceDataInteractionPage
	 * @return
	 */
	public Json addWebServiceData(Integer id);

}
