package com.dbsoft.whjd.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.WebServiceDataInteractionPage;
import com.dbsoft.whjd.service.IWebServiceDataService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "webServiceDataAction")
public class WebServiceDataAction extends BaseAction implements
		ModelDriven<WebServiceDataInteractionPage> {
	WebServiceDataInteractionPage webServiceDataInteractionPage = new WebServiceDataInteractionPage();
	IWebServiceDataService webServiceDataService;
	private Logger logger = Logger.getLogger(WebServiceDataAction.class);

	public void addWebServiceData() {
		Json res = new Json();
		try {
			// 调用service的方法
			if (webServiceDataInteractionPage.getId() == null) {
				res.setSuccess(false);
				res.setMsg("未传入委托单Id");
			} else {
				res = webServiceDataService
						.addWebServiceData(webServiceDataInteractionPage
								.getId());
			}

		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("添加检测任务出现异常");
		}
		super.writeJson(res);
	}

	@Override
	public WebServiceDataInteractionPage getModel() {
		// TODO Auto-generated method stub
		return webServiceDataInteractionPage;
	}

	public IWebServiceDataService getWebServiceDataService() {
		return webServiceDataService;
	}

	@Autowired
	public void setWebServiceDataService(
			IWebServiceDataService webServiceDataService) {
		this.webServiceDataService = webServiceDataService;
	}

}
