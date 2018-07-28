package com.dbsoft.whjd.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionLineService;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.service.impl.DetectionLineServiceImpl;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "detectionLineAction", results = { @Result(name = "edit_detectionLine", location = "/jsp/DetectionLineManage/editDetectionLine.jsp") })
public class DetectionLineAction extends BaseAction implements
		ModelDriven<DetectionLinePage> {
	private DetectionLinePage detectionLinePage = new DetectionLinePage();
	private static final Logger logger = Logger
			.getLogger(VehicleLimitAction.class);
	private IDetectionLineService detectionLineService;
	//private IHuanBaoBuService huanBaoBuService;
	private static String EDIT_DETECTION_LINE = "edit_detectionLine";

	/**
	 * 添加检测线信息
	 */
	public void addDetectionLine() {
		Json res = new Json();
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			int stationIdVal=Integer.parseInt(session.getAttribute("stationId").toString());
			if(stationIdVal!=0)
			{
				detectionLinePage.setStationId(stationIdVal);
			}
			res = detectionLineService.addDetectionLine(detectionLinePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("检测站不存在，请从下拉列表选择");
		}
		//huanBaoBuService.addDetectionLine(detectionLinePage);
		super.writeJson(res);
	}

	/**
	 * 检测是否存在名字
	 */

	public void hasLineName() {

		Json json = new Json();
		try {
			json = detectionLineService.hasLineName(detectionLinePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("检测名字失败");
		}
		super.writeJson(json);
	}

	/**
	 * 检测是否存在名字
	 */

	public void hasLocaleId() {

		Json json = new Json();
		try {
			json = detectionLineService.hasLocaleId(detectionLinePage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("检测站内编号失败");
		}
		super.writeJson(json);
	}

	/**
	 * 获得所有的检测线信息
	 */
	public void getAllDetectionLine() {
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionLinePage.setStationName(stationNameStr);
			}
			DataGrid res = detectionLineService
					.getAllDetectionLine(detectionLinePage);
			super.writeJson(res);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 注销检测线，支持批量操作
	 */
	public void cancelDetectionLine() {
		Json json = new Json();
		try {
			// TODO调用service的方法进行注销
			json = detectionLineService.cancelDetectionLine(detectionLinePage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("注销检测线信息失败");
			super.writeJson(json);
		}
	}

	/**
	 * 删除检测线，支持批量操作(便于以后的功能扩展)
	 */
	public void deleteDetectionLine() {
		Json json = new Json();
		try {
			// TODO调用service的方法进行注销
			json = detectionLineService.deleteDetectionLine(detectionLinePage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("删除检测线信息失败");
			super.writeJson(json);
		}
	}

	/**
	 * 修改检测线的信息
	 */
	public void modifyDetectioLine() {
		Json res = new Json();
		try {
			res = detectionLineService.modifyDetectionLine(detectionLinePage);

		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("修改检测线信息失败");
		}
		super.writeJson(res);
	}

	/**
	 * 进入edit的对话框方法
	 */
	public String editDetectioLine() {
		return EDIT_DETECTION_LINE;
	}

	/**
	 * 获取检测线的名称
	 */
	public void getDetectionLineName() {
		List<DetectionLinePage> list = new ArrayList<DetectionLinePage>();
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionLinePage.setStationName(stationNameStr);
			}
			list = detectionLineService.getDetectionLineName(detectionLinePage);

		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(list);

	}
	/**
	 * 获取已经标定的检测站中检测线的名称
	 */
	public void getDetectionLineNameCarlibrated() {
		List<DetectionLinePage> list = new ArrayList<DetectionLinePage>();
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr=session.getAttribute("stationName").toString();
			if(!stationNameStr.equals("市局"))
			{
				detectionLinePage.setStationName(stationNameStr);
			}
			detectionLinePage.setIsCarlibrated(1);
			list = detectionLineService.getDetectionLineName(detectionLinePage);
			
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(list);
		
	}
	
	/**
	 * 导出信息方法
	 */
	public void exportDetectionLine(){
		Json res=new Json();
		res.setSuccess(true);
	  try{
		  res=detectionLineService.exportDetectionLine(detectionLinePage);
	  }catch (Exception e) {
		  logger.error(ExceptionUtil.getExceptionMessage(e));
		  res.setSuccess(false);
		  res.setMsg("导出发生异常");
	  }
		super.writeJson(res);
	}

	@Override
	public DetectionLinePage getModel() {
		return detectionLinePage;
	}

	public IDetectionLineService getDetectionLineService() {
		return detectionLineService;
	}

	@Autowired
	public void setDetectionLineService(
			IDetectionLineService detectionLineService) {
		this.detectionLineService = detectionLineService;
	}
/*
	public IHuanBaoBuService getHuanBaoBuService() {
		return huanBaoBuService;
	}
	@Autowired
	public void setHuanBaoBuService(IHuanBaoBuService huanBaoBuService) {
		this.huanBaoBuService = huanBaoBuService;
	}
	*/

}
