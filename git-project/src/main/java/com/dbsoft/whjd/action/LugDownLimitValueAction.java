package com.dbsoft.whjd.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;


@Action(value="lugDownLimitValueAction",results={
		@Result(name="edit",location="/jsp/limitValueReference/editLugDownLimitValue.jsp")
})
public class LugDownLimitValueAction extends BaseAction implements ModelDriven<LimitValueReferencePage>{
    private LimitValueReferencePage limitValueReferencePage=new LimitValueReferencePage();
    private ILimitValueReferenceService lugdownLimitValueService;
    private Logger logger=Logger.getLogger(LugDownLimitValueAction.class);
    private static final String EDIT_LIMITVALUE="edit";
    /**
     * 添加对应的加载减速法限值
     */
    public void addLugDownLimitValue()
    {
    	Json res=new Json();
    	try {
			//调用service里面的方法
    		res=lugdownLimitValueService.saveLimitValue(limitValueReferencePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
    		res.setSuccess(false);
    		res.setMsg("添加加载减速法限值出现异常");
		}
    	super.writeJson(res);
    }
    /**
     * 获取所有的加载减速法限值
     */
    public void getAllLimitValues()
    {
    	DataGrid dg=new DataGrid();
    	try {
			dg=lugdownLimitValueService.getLimitValues(limitValueReferencePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
    	super.writeJson(dg);
    }
    /**
     * 删除对应的记录
     */
    public void deleteLimitValues()
    {
    	Json res=new Json();
    	try {
			//调用service中的方法进行删除相应的限值记录
    		res=lugdownLimitValueService.deleteLimitValue(limitValueReferencePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
    		res.setSuccess(false);
    		res.setMsg("删除加载减速法限值出现异常");
		}
    	super.writeJson(res);
    }
    public String edit()
    {
    	return EDIT_LIMITVALUE;
    }
    /**
     * 修改对应的限值记录
     */
    public void modifyLimitValue()
    {
    	Json res=new Json();
    	try {
			res=lugdownLimitValueService.editLimitValue(limitValueReferencePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
    		res.setSuccess(false);
    		res.setMsg("修改加载减速法限值出现异常");
		}
    	super.writeJson(res);
    }
    @Override
	public LimitValueReferencePage getModel() {
		return limitValueReferencePage;
	}
    
    
	public ILimitValueReferenceService getLugdownLimitValueService() {
		return lugdownLimitValueService;
	}
	
	@Autowired
	@Qualifier("lugdownLimitValueServiceImpl")
	public void setLugdownLimitValueService(
			ILimitValueReferenceService lugdownLimitValueService) {
		this.lugdownLimitValueService = lugdownLimitValueService;
	}
    
}
