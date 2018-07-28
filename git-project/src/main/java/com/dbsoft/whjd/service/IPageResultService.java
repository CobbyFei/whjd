package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.PageResult;




public interface IPageResultService {
	
	/**
	 * 返回后台操作成功的结果，resultPage
	 * @return DTO中的PageResult
	 */
	public PageResult getSuccessResult();
	
	/**
	 * 返回后台操作的具体结果，resultPage包含说明
	 * resultMsg为具体说明内容
	 * @return DTO中的PageResult
	 */
	public PageResult getSpecialResult(String resultMsg);
	
}
