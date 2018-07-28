package com.dbsoft.whjd.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TrainingRecordPage;
import com.dbsoft.whjd.service.ITrainingRecordService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理培训以及考核记录
 * @author mark
 *
 */
@Action(value = "trainingRecordAction", results = {
		@Result(name ="trainingRecordEdit", location="/jsp/TrainingRecordManage/trainingRecordEdit.jsp")
})
public class TraningRecordAction extends BaseAction implements ModelDriven<TrainingRecordPage> {
	
	private static final Logger logger = Logger.getLogger(ManageUserAction.class);
	
	@Autowired
	private ITrainingRecordService trainingRecordService;
	
	private TrainingRecordPage trainingRecordPage = new TrainingRecordPage();
	
	
	private String q;
	
	public String getQ() {
		return q;
	}
	
	public void setQ(String q) {
		this.q = q;
	}
	
	/**
	 * 新增培训或考核记录
	 * @author mark
	 */
	public void addTrainingRecord(){
		try{
			Json json = trainingRecordService.addTrainingRecord(trainingRecordPage);			
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	/**
	 * 查询培训或考核记录
	 * @author mark
	 */
	public void getTrainingRecordDatagrid(){
		 try{
	    	DataGrid dg =trainingRecordService.findTrainingRecords(trainingRecordPage);
	    	if(dg == null){
	    		throw new Exception();
	    	}else{
	    		super.writeJson(dg);
		    }
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("TrainingRecordDatagrid 返回值为空！");
		}
	}
	
	/**
	 * 页面转换至修改页并传递参数
	 * @author mark
	 * @return
	 */
	public String trainingRecordEdit(){
		return "trainingRecordEdit";
	}
	/**
	 * 修改培训或考核记录
	 * @author mark
	 */
	public void editTrainingRecord(){
		System.out.println(trainingRecordPage.toString());
		try{
			System.out.println(trainingRecordPage.getUserId()+trainingRecordPage.getStationId());
			Json json = trainingRecordService.updateTrainingRecord(trainingRecordPage);
			if(json == null){
				throw new Exception();
			}else{
				super.writeJson(json);
			}
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("Json 返回值为空！");
		}	
	}
	
	public void changeTrainingRecordStatus(){
		try{
			Json json = trainingRecordService.changeTrainingRecordStatus(trainingRecordPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	
	@Override
	public TrainingRecordPage getModel(){
		return trainingRecordPage;
	}
}
