package com.dbsoft.whjd.service.impl;

import org.springframework.stereotype.Service;

import com.dbsoft.whjd.pageModel.PageResult;
import com.dbsoft.whjd.service.IPageResultService;


@Service("pageResultService")
public class PageResultServiceImpl implements IPageResultService {

	@Override
	public PageResult getSuccessResult() {
		PageResult r = new PageResult();
		r.setSuccess(true);
		return r;
	}

	@Override
	public PageResult getSpecialResult(String resultMsg) {
		PageResult r = new PageResult();
		r.setSuccess(false);
		r.setMsg(resultMsg);
		return r;
	}

}
