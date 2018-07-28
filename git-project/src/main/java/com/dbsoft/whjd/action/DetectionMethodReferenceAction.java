package com.dbsoft.whjd.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionMethodReferencePage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionMethodReferenceService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
@Action(value="detectionMethodReferenceAction",results={
	@Result(name="edit",location="/jsp/DetectionMethodReferenceManage/editDetectionMethodReference.jsp")
})
public class DetectionMethodReferenceAction extends BaseAction implements ModelDriven<DetectionMethodReferencePage>{
    private DetectionMethodReferencePage detectionMethodReferencePage=new DetectionMethodReferencePage();
	private IDetectionMethodReferenceService detectionMethodReferenceService;
    private static final Logger logger=Logger.getLogger(DetectionMethodReferenceAction.class);
	private static final String EDIT_DETECTIONMETHODREFERENCE="edit";
    /**
     * 增加方法参照信息
     */
    public void addDetectionMethodReference(){
    	  Json res=new Json();
    	  try{
    		  res=detectionMethodReferenceService.addDetectionMethodReference(detectionMethodReferencePage);
    	  }catch (Exception e) {
	    		logger.error(ExceptionUtil.getExceptionMessage(e));
	  			res.setSuccess(false);
	  			res.setMsg("检测站不存在，请从下拉列表选择");
		  }
    	  super.writeJson(res);
    	
    }
    
    /**
     * 删除检测方法信息
     */
    public void deleteDetectionMethodReference(){
    	Json res=new Json();
    	try{
    		//TODO 调用service方法进行删除
    		res=detectionMethodReferenceService.deleteDetecitonMethodReference(detectionMethodReferencePage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
  			res.setSuccess(false);
  			res.setMsg("出现删除异常");
		}
    	super.writeJson(res);
    }
    
    /**
     * 获取所有的检测方法参照记录，或者查出符合条件的记录
     */
    public void getAllDetectionMethodReferenceRecord()
    {
    	DataGrid dg=new DataGrid();
    	try{
    		  //TODO 调用service方法进行获取数据
    		  dg=detectionMethodReferenceService.getAllDetectionMethodReference(detectionMethodReferencePage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("获取数据错误");
		}
    	super.writeJson(dg);
    }
    
    /**
     * 修改检测方法参照记录
     */
    public void modifyDetectionMethodReferenceRecord()
    {
    	  Json res=new Json();
    	  try{
    		  res=detectionMethodReferenceService.modifyDetectionMethodReference(detectionMethodReferencePage);
    	  }catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
  			res.setSuccess(false);
  			res.setMsg("修改检测方法参照记录失败");
		}
    	 super.writeJson(res);
    }
    /**
     * 返回页面
     * @return
     */
    public String edit(){
    	return EDIT_DETECTIONMETHODREFERENCE;
    }
    /**
     * 联想操作使用
     */
    public void getDetectionMethodList(){
    	List<DetectionMethodReferencePage> list=new ArrayList<DetectionMethodReferencePage>();
    	try{
    		list=detectionMethodReferenceService.getAllDetectionMethodReferenceList(detectionMethodReferencePage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("出现错误");
		}
    	super.writeJson(list);
    }
    /**
     * 判断检测方法是否存在
     */
    public void hasMethodName(){
    	Json res=new Json();
    	try{
    		res=detectionMethodReferenceService.hasMethodName(detectionMethodReferencePage);
    	}catch(Exception e)
    	{
    		logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("检测名字失败");
    	}
    	super.writeJson(res);
    }
    
    /**
     * 根据车身长度、燃油类型以及最大总质量 返回相应采用检测方法
     */
    public void getDetectionMethod(){
    	Json res=new Json();
    	try{
    		res=detectionMethodReferenceService.getDetectionMethod(detectionMethodReferencePage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("获取检测方法出现错误");
		}
    	super.writeJson(res);
    }
    
    @Override
	public DetectionMethodReferencePage getModel() {
		return detectionMethodReferencePage;
	}

	public IDetectionMethodReferenceService getDetectionMethodReferenceService() {
		return detectionMethodReferenceService;
	}
    @Autowired
	public void setDetectionMethodReferenceService(
			IDetectionMethodReferenceService detectionMethodReferenceService) {
		this.detectionMethodReferenceService = detectionMethodReferenceService;
	}
	
   
}
