package com.dbsoft.whjd.action;

import javax.enterprise.inject.New;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TestInteractPage;
import com.dbsoft.whjd.service.ITestInteractService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;


/**
 * 接口的交互测试类
 * @author wzr
 */
@Action(value="testAction")
public class TestAction extends BaseAction implements ModelDriven<TestInteractPage>{
    private TestInteractPage testInteractPage=new TestInteractPage();
	private Logger logger=Logger.getLogger(TestAction.class);
    private ITestInteractService testInteractService;
	
	
	public ITestInteractService getTestInteractService() {
		return testInteractService;
	}
	@Autowired
	public void setTestInteractService(ITestInteractService testInteractService) {
		this.testInteractService = testInteractService;
	}
	/**
     * 进行交互
     * 交互地址： http://10.33.35.208:8080/whjd/testAction!interact.action
     */
	
    public void interact(){
    	Json  res=new Json();
    	try{
    		System.out.println(testInteractPage.getCallback());
    		res=testInteractService.interact(testInteractPage);
    	}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("交互出现异常");
		}
    	super.writeJson(testInteractPage.getCallback()+res);
    }
    @Override
	public TestInteractPage getModel(){
		return testInteractPage;
	}

}
