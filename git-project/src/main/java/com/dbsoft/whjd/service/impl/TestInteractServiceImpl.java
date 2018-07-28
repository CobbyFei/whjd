package com.dbsoft.whjd.service.impl;

import org.springframework.stereotype.Service;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TestInteractPage;
import com.dbsoft.whjd.service.ITestInteractService;
@Service("testInteractService")
public class TestInteractServiceImpl implements ITestInteractService{

	@Override
	public Json interact(TestInteractPage testInteractPage) {
		Json res=new Json();
		System.out.println("Id="+testInteractPage.getId());
		System.out.println("methodName="+testInteractPage.getMethodName());
		res.setSuccess(true);
		res.setMsg("远程调用方法成功");
		return res;
	}
	

}
