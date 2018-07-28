package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TestInteractPage;

/**
 * 用于测试双方交互的service
 * @author wzr
 *
 */
public interface ITestInteractService {
	/**
	 * 交互的方法
	 * @param testInteractPage
	 * @return
	 */
    public Json interact(TestInteractPage testInteractPage);
}
