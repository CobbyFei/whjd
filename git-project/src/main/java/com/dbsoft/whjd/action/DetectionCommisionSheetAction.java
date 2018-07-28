package com.dbsoft.whjd.action;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.DetectionMethodReferencePage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionCommisionSheetService;
import com.dbsoft.whjd.service.IDistributionTaskService;
import com.dbsoft.whjd.service.IEmissionStandardService;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="detectionCommisionSheetAction",results={
	@Result(name="edit",location="/jsp/DetectionCommisionSheetManage/editDetectionCommisionSheet.jsp")
})
public class DetectionCommisionSheetAction extends BaseAction implements ModelDriven<DetectionCommisionSheetPage>{
    private DetectionCommisionSheetPage detectionCommisionSheetPage=new DetectionCommisionSheetPage();
    private IDetectionCommisionSheetService detectionCommisionSheetService;
    private IEnvironmentalLabelService  environmentalLabelService;
    private IDistributionTaskService distributionTaskService;
    private IEmissionStandardService emissionStandardService;
	
	
    

	private Logger logger=Logger.getLogger(DetectionCommisionSheetAction.class);
    private static final String EDIT_DETECTIONCOMMISIONSHEET="edit";
	/**
	 * 新建委托单
	 */
    public void addDetectionCommisionSheet(){
    	Json res=new Json();
    	try{
    		HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionCommisionSheetPage.setStationName(stationNameStr);
			}
    		res=detectionCommisionSheetService.addDetectionCommisionSheet(detectionCommisionSheetPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		res.setSuccess(false);
    		res.setMsg("添加检测委托单出现异常");
		}
    	super.writeJson(res);
    }

    /**
     * 获取委托单信息
     */
    public void getAllDetectionCommisionSheet(){
    	DataGrid dg=new DataGrid();
    	try{
    		HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionCommisionSheetPage.setStationName(stationNameStr);
			}
    		dg=detectionCommisionSheetService.getAllDetectionCommisionSheet(detectionCommisionSheetPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("获取数据出现错误");
		}
    	super.writeJson(dg);
    }
    
    /**
     * 车辆信息预读
     */
    public void getVehibleInfo(){
    	DataGrid dg=new DataGrid();
    	try{
    		HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionCommisionSheetPage.setStationName(stationNameStr);
			}
    		dg=detectionCommisionSheetService.getVehibleInfo(detectionCommisionSheetPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("获取数据出现错误");
		}
    	super.writeJson(dg);
    }
    /**
     * 注销委托单
     * @return
     */
   public void cancelDetectionCommistionSheet(){
	   Json res=new Json();
	   try {
             res=detectionCommisionSheetService.cancelDetectionCommisionSheet(detectionCommisionSheetPage);
	   } catch (Exception e) {
		    logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("注销出现错误");
			res.setSuccess(false);
		}
	    super.writeJson(res);
   }
   /**
    * 挡回委托单
    * @return
    */
  public void revokeDetectionCommistionSheet(){
	   Json res=new Json();
	   try {
            res=detectionCommisionSheetService.revokeDetectionCommistionSheet(detectionCommisionSheetPage);
	   } catch (Exception e) {
		    logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("挡回委托单出现错误");
			res.setSuccess(false);
		}
	    super.writeJson(res);
  }
   /**
    * 返回委托单修改页面
    */
   public String edit()
   {
	   return EDIT_DETECTIONCOMMISIONSHEET;
   }
   /**
    * 修改委托单信息
    */
   public void modifyDetectionCommisionSheet(){
	  Json res=new Json();
	  try {
		 res=detectionCommisionSheetService.modifyDetectionCommisionSheet(detectionCommisionSheetPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("修改出现错误");
			res.setSuccess(false);
		}
	  super.writeJson(res);
   }
   
	   /**
	    * 判断是否是核发
	    * @return
	    */
    public void isFirstDetecetd(){
    	Json res=new Json();
    	try{
    		System.out.println("进入action");
    		res=detectionCommisionSheetService.checkIsFirstDetected(detectionCommisionSheetPage);
    	}catch (Exception e) {
    	    logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("查询是否核发出现错误");
		    res.setSuccess(false);
		}
    	super.writeJson(res);
    }
    
    /**
     * 对已经下结论合格的非外地车辆进行发放标志
     * @return
     */
    public void distributeLabel()
    {
    	Json res=new Json();
    	try {
			    		
    		res=distributionTaskService.addDistributionTask(detectionCommisionSheetPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("标志发放出现错误");
		    res.setSuccess(false);
		}
    	super.writeJson(res);
    }
    
    public void reDistributeLabel()
    {
    	Json res=new Json();
    	try {
			    		
    		res=distributionTaskService.reDistributionTask(detectionCommisionSheetPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("标志重新发放出现异常");
		    res.setSuccess(false);
		}
    	super.writeJson(res);
    }
    //导出检测委托单信息
    public void exportDetectionCommisionSheet(){
    	Json res=new Json();
		res.setSuccess(true);
		try{
		   res=detectionCommisionSheetService.exportDetectionCommisionSheet(detectionCommisionSheetPage);
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		    res.setSuccess(false);
			res.setMsg("导出发生异常");
		  }
		super.writeJson(res);
    }
    
    //新车准入，将新车信息存入新车准入证明表和检测委托单表，便于信息预读。
    public void addNewCarAdmitInfo(){
    	Json res=new Json();
    	res.setSuccess(true);
    	res.setMsg("新车信息录入成功，现在可以去打印页面进行打印");
    	try {
			emissionStandardService.addNewCarAdmitInfo(detectionCommisionSheetPage);
			emissionStandardService.addDetectionRecord(detectionCommisionSheetPage);
		} catch (Exception e) {
			res.setSuccess(false);
			res.setMsg("新车信息录入失败");
		}
		super.writeJson(res);
    }
    
    public void getEmissionStandard(){
    	DataGrid dg=new DataGrid();
    	try{
    		dg=detectionCommisionSheetService.getEmissionStandard(detectionCommisionSheetPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("获取排放标准数据出现错误");
		}
    	super.writeJson(dg);
    }
    
    public void getVehicleInfoByHBTing(){
    	DataGrid dg=new DataGrid();
    	try{
    		dg=detectionCommisionSheetService.getVehicleInfoByHBTing(detectionCommisionSheetPage);
    	}catch (Exception e) {
    		logger.error(ExceptionUtil.getExceptionMessage(e));
    		System.out.println("获取车辆基本信息出现错误");
		}
    	super.writeJson(dg);
    }
    
    public IDetectionCommisionSheetService getDetectionCommisionSheetService() {
		return detectionCommisionSheetService;
	}
    @Autowired
	public void setDetectionCommisionSheetService(
			IDetectionCommisionSheetService detectionCommisionSheetService) {
		this.detectionCommisionSheetService = detectionCommisionSheetService;
	}

	@Override
	public DetectionCommisionSheetPage getModel() {
		// TODO Auto-generated method stub
		return detectionCommisionSheetPage;
	}

	public IEnvironmentalLabelService getEnvironmentalLabelService() {
		return environmentalLabelService;
	}
    @Autowired
	public void setEnvironmentalLabelService(
			IEnvironmentalLabelService environmentalLabelService) {
		this.environmentalLabelService = environmentalLabelService;
	}
     
    public IDistributionTaskService getDistributionTaskService() {
		return distributionTaskService;
	}
    @Autowired
	public void setDistributionTaskService(IDistributionTaskService distributionTaskService) {
		this.distributionTaskService = distributionTaskService;
	} 
    
    public IEmissionStandardService getEmissionStandardService() {
		return emissionStandardService;
	}
	@Autowired
	public void setEmissionStandardService(IEmissionStandardService emissionStandardService) {
		this.emissionStandardService = emissionStandardService;
	}
     

}
