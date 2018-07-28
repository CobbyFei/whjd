package com.dbsoft.whjd.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.CalibrationRecordPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionLineCalibrationService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 检测线的action
 * @author wzr
 *
 */

@Action(value="calibrationDetectionLineAction",results={
		@Result(name="edit",location="/jsp/DetectionLineCalibrationManage/editDetectionLineCalibrationRecord.jsp")
})
public class CalibrationDetectionLineAction extends BaseAction implements ModelDriven<CalibrationRecordPage>{
    private  CalibrationRecordPage calibrationRecordPage=new CalibrationRecordPage();
    private static final Logger logger=Logger.getLogger(CalibrationDetectionLineAction.class);
    private IDetectionLineCalibrationService detectionLineCalibrationService;
    private static final String EDIT_CALIBRATIONRECORD="edit";
    /**
     * 添加检测线标定记录
     */
    public void addCalibrationRecord(){
    	Json res=new Json();
    	try{
    		res=detectionLineCalibrationService.addCalibrationRecord(calibrationRecordPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("添加检测线标定记录失败");
		}
    	super.writeJson(res);
    	
    }
    
    /**
     * 获得所有的检测标定记录
     */
    public void getAllCalibrationRecord()
    {
    	DataGrid dg=new DataGrid();
    	try{
    		HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				calibrationRecordPage.setStationName(stationNameStr);
			}
    		dg=detectionLineCalibrationService.getAllCalibrationRecord(calibrationRecordPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
		}
    	super.writeJson(dg);
    }
    
   /**
    * 删除检测线标定记录
    */
    public void deleteCalibrationRecord(){
    	Json res=new Json();
    	try{
    		res=detectionLineCalibrationService.deleteCalibrationRecord(calibrationRecordPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("删除检测线标定记录失败");
		}
    	super.writeJson(res);
    }
    
    /**
     * 修改检测线的标定记录
     */
    public void modifyCalibrationRecord()
    {
    	Json res=new Json();
    	try{
    		//TODO 调用service的方法就行信息的修改
    		res=detectionLineCalibrationService.modifyCalibrationRecord(calibrationRecordPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("修改检测线标定记录失败");
		}
    	super.writeJson(res);
    }
    
    /**
     * 注销检测线的标定记录
     */
    public void cancelRecord(){
        Json res=new Json();
        try{
        	res=detectionLineCalibrationService.cancelCalibrationRecord(calibrationRecordPage);
        }catch (Exception e) {
        	logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("注销检测线标定记录失败");
		}
        super.writeJson(res);
    }
    
    /**
     * 返回到编辑页面
     */
    public String editCalibrationRecord(){
    	return EDIT_CALIBRATIONRECORD;
    }
    /**
     * 导出检测线标定记录
     */
    public void exportLineCarlibration(){
    	Json res=new Json();
		  try{
			 res=detectionLineCalibrationService.exportLineCarlibration(calibrationRecordPage);
		  }catch (Exception e) {
			  logger.error(ExceptionUtil.getExceptionMessage(e));
			  res.setSuccess(false);
			  res.setMsg("导出发生异常");
		  }
		super.writeJson(res);
    }
    
	@Override
	public CalibrationRecordPage getModel() {
		return calibrationRecordPage;
	}


	public IDetectionLineCalibrationService getDetectionLineCalibrationService() {
		return detectionLineCalibrationService;
	}

    @Autowired
	public void setDetectionLineCalibrationService(
			IDetectionLineCalibrationService detectionLineCalibrationService) {
		this.detectionLineCalibrationService = detectionLineCalibrationService;
	}
      
}
