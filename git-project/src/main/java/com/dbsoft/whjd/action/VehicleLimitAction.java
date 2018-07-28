package com.dbsoft.whjd.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dbsoft.whjd.pageModel.BlackNameListPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IVehicleLimitRecordService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author wzr
 *
 */
@Action(value="vehicleLimitAction",
        results={
		@Result(name="editVehicleLimitRecord",location="/jsp/VehicleLimitManage/editVehicleLimitRecord.jsp")
       }
)
public class VehicleLimitAction extends BaseAction  implements ModelDriven<BlackNameListPage>{
	private IVehicleLimitRecordService vehicleLimitRecordService;
    


	private static final Logger logger = Logger.getLogger(VehicleLimitAction.class);
	private BlackNameListPage blackNameListPage=new BlackNameListPage();

	private static String EDIT_VEHICLE_LIMIT_RECORD="editVehicleLimitRecord";
	/**
	 * 添加车辆违规限行记录
	 */
	public void addVehicleLimitRecord()
	{
//		System.out.println(blackNameListPage.get);
		Json res=new Json();
		try{
			vehicleLimitRecordService.addVehicleLimitRecord(blackNameListPage);
			System.out.println(blackNameListPage.getLicenceColor());
			res.setSuccess(true);
			res.setMsg("添加成功");
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("添加失败");
		}
		super.writeJson(res);
	}
	
	public void exportVehicleLimit(){
		Json res=new Json();
		  try{
			 res=vehicleLimitRecordService.exportVehicleLimit(blackNameListPage);
		  }catch (Exception e) {
			  logger.error(ExceptionUtil.getExceptionMessage(e));
			  res.setSuccess(false);
			  res.setMsg("导出发生异常");
		  }
		super.writeJson(res);
	}
	/**
	 * 获取所有的车辆违规记录
	 */
	public void getAllVehicleLimitRecord()
	{
		//TODO d调用service的get方法
		
	    try{
	    	DataGrid res=vehicleLimitRecordService.getAllVechileLimitRecord(blackNameListPage);
		    super.writeJson(res);
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String vehicleLimitRecordEdit()
	{
		return EDIT_VEHICLE_LIMIT_RECORD;
	}
	/**
	 * 注销车辆限行信息
	 */
	public void cancelVehicleLimitRecord()
	{
		Json res=new Json();
	    try{
	    	 res=vehicleLimitRecordService.cancelVehicleLimitRecord(blackNameListPage);
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	res.setSuccess(false);
			res.setMsg("注销车辆限行信息失败");
		}
	    super.writeJson(res);
	}
	/**
	 *  编辑修改车辆限行信息
	 */
	public void modifyVehicleLimitRecord()
	{
		Json res=new Json();
	    try{
	    	System.out.println(blackNameListPage.getId());
	    	System.out.println(blackNameListPage.getIsCancelPage());
	    	res=vehicleLimitRecordService.modifyVehicleLimitRecord(blackNameListPage);
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	res.setSuccess(false);
			res.setMsg("修改车辆限行信息失败");
		}
	    super.writeJson(res);
	}
	/**
	 * 删除选中的车辆违规限行信息(为以后的扩展调用)
	 */
	public void deleteVehicleLimitRecord()
	{
		Json res=new Json();
	    try{
	    	res=vehicleLimitRecordService.deleteVehicleLimitRecord(blackNameListPage);
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	res.setSuccess(false);
			res.setMsg("删除车辆限行信息失败");
		}
	    super.writeJson(res);
	}
	/**
	 * 判断是否存在违章记录
	 */
	public void isExistsVehicleLimit()
	{
		Json res=new Json();
		try{
			res=vehicleLimitRecordService.isBlacKNameExists(blackNameListPage);
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	res.setSuccess(false);
			res.setMsg("查询出现异常");
		}
		super.writeJson(res);
	}
	/**
	 * 从外部导入Excel违章记录
	 */
	public void importExcel()
	{
		Json json=new Json();
		json=vehicleLimitRecordService.copyToExcel(blackNameListPage);
	    if(json.isSuccess()==false)
	    {
	    	super.writeJson(json);
	    	return;
	    }
	    blackNameListPage.setFilePath(json.getMsg());
	    json=vehicleLimitRecordService.importVehicleLimit(blackNameListPage);
	    super.writeJson(json);
	}
	@Override
	public BlackNameListPage getModel() {
		// TODO Auto-generated method stub
		return blackNameListPage;
	}

	public IVehicleLimitRecordService getVehicleLimitRecordService() {
		return vehicleLimitRecordService;
	}

   @Autowired
	public void setVehicleLimitRecordService(
			IVehicleLimitRecordService vehicleLimitRecordService) {
		this.vehicleLimitRecordService = vehicleLimitRecordService;
	}
	
	
	

}
