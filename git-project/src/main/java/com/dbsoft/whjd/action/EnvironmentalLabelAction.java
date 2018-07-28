package com.dbsoft.whjd.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dbsoft.whjd.pageModel.EnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelPage;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IEnvironmentalLabelCollarService;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.dbsoft.whjd.util.TwoDimensionCode;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "environmentalLabelAction", results = {
		@Result(name = "editEnvironmentalLabel", location = "/jsp/EnvironmentalLabelManage/modifyEnvironmentalLabel.jsp"),
		// @Result(name = "labelPrint", location =
		// "/jsp/EnvironmentalLabelManage/printEnvironmentalLabel.jsp") })
		@Result(name = "labelPrint", location = "/jsp/EnvironmentalLabelManage/printPassInformSheet.jsp") })
public class EnvironmentalLabelAction extends BaseAction implements ModelDriven<EnvironmentalLabelPage> {
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	private static final Logger logger = Logger.getLogger(EnvironmentalLabelCollarAction.class);
	private EnvironmentalLabelPage environmentalLabelPage = new EnvironmentalLabelPage();

	public IEnvironmentalLabelService getEnvironmentalLabelService() {
		return environmentalLabelService;
	}

	@Resource(name = "environmentalLabelService")
	public void setEnvironmentalLabelService(IEnvironmentalLabelService environmentalLabelService) {
		this.environmentalLabelService = environmentalLabelService;
	}

	public static Logger getLogger() {
		return logger;
	}

	private IEnvironmentalLabelService environmentalLabelService;

	@Override
	public EnvironmentalLabelPage getModel() {
		// TODO Auto-generated method stub
		return environmentalLabelPage;
	}

	public void cancelEnvironmentalLabel() { // 注销环保标志记录
		Json json = new Json();
		try {
			json = environmentalLabelService.cancelEnvironmentalLabel(environmentalLabelPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("注销环保标志领用记录失败");
		}
		super.writeJson(json);
	}

	public String editEnvironmentalLabel() {// 跳转编辑页面
		return "editEnvironmentalLabel";
	}

	public void updateEnvironmentalLabel() { // 修改环保标志记录
		Json json = new Json();
		try {
			json = environmentalLabelService.updateEnvironmentalLabel(environmentalLabelPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("修改环保标志记录失败");
		}
		super.writeJson(json);
	}

	public void addEnvironmentalLabel() {
		// 添加环保标志记录
		Json json = new Json();
		try {
			//json = environmentalLabelService.addEnvironmentalLabel("");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("注销环保标志领用记录失败");
		}
		super.writeJson(json);
	}

	public void getEnvironmentalLabel() { // 获取环保标志记录
		try {
			super.writeJson(environmentalLabelService.getEnvironmentalLabel(environmentalLabelPage));
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}

	public String labelPrint() {
		HttpServletRequest req = ServletActionContext.getRequest();
		EnvironmentalLabelPage page = environmentalLabelService
				.getEnvironmentalLabelById(environmentalLabelPage.getId());
		req.getSession().setAttribute("labelPage", page);
		return "labelPrint";
	}

	public void outPutQRcode() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// 调用service的方法返回环保标志信息
		String licence = environmentalLabelPage.getLicence();
		String labelId = environmentalLabelPage.getLabelId();
		String url = "http://www.vecc-mep.org.cn/gl2010/";
		String content = "车牌号:" + licence + "\n环保标志号:" + labelId + "\n" + "防伪查询网站:" + url + "\n";
		System.out.println(content);
		TwoDimensionCode handler = new TwoDimensionCode();
		try {
			handler.encoderQRCode(content, resp.getOutputStream());
			resp.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPrinted() {
		super.writeJson(environmentalLabelService.setIsPrint(environmentalLabelPage.getId()));
	}
}
