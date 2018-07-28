package com.dbsoft.whjd.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.SteadyStateMethod;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.UserLoginPage;

public class XMLUtils {
	private volatile static XMLUtils xmlUtils;

	private XMLUtils() {
	}

	public static XMLUtils getInstance() {
		if (xmlUtils == null) {
			synchronized (XMLUtils.class) {
				xmlUtils = new XMLUtils();
			}
		}
		return xmlUtils;
	}

	/**
	 * 根据用户名从xml文件中读取指定的用户登录信息
	 * 
	 * @param simplifyName
	 * @return
	 * @throws DocumentException
	 */
	public UserLoginPage readFromXML(String simplifyName) throws DocumentException {
		UserLoginPage res = new UserLoginPage();
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "/loginState.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String filePath="/loginState.xml";
		SAXReader sr = new SAXReader();
		Document document = sr.read(filePath);
		Element el_root = document.getRootElement();
		Iterator iterator = el_root.elementIterator();
		while (iterator.hasNext()) {
			Element el_user = (Element) iterator.next();
			// 遍历user一下的节点
			Iterator el_user_iterator = el_user.elementIterator();
			if (el_user_iterator.hasNext()) {
				Element el_userId = (Element) el_user_iterator.next();
				String simpleName = el_userId.getText();
				if (simpleName.equals(simplifyName)) {
					Element el_loginTime = (Element) el_user_iterator.next();
					res.setSimplifyName(simplifyName);
					res.setRefreshTime(el_loginTime.getText());
					Element el_loginState = (Element) el_user_iterator.next();
					res.setLoginState(el_loginState.getText());
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * flag=0 表示已经存在该记录，在xml修改对应的值。flag=1表示需要增加节点
	 * 
	 * @param userLoginPage
	 * @param flag
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void writeIntoXML(UserLoginPage userLoginPage, int flag) throws DocumentException, IOException {
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "/loginState.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAXReader sr = new SAXReader();
		Document document = sr.read(filePath);
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		Element el_root = document.getRootElement();
		if (userLoginPage == null)
			return;
		if (flag == 1) {
			Element user = el_root.addElement("user");
			Element userId = user.addElement("userId");
			userId.setText(userLoginPage.getSimplifyName());
			Element refresh = user.addElement("refreshTime");
			refresh.setText(userLoginPage.getRefreshTime());
			Element loginState = user.addElement("loginState");
			loginState.setText(userLoginPage.getLoginState());
			writer = new XMLWriter(new FileOutputStream(filePath), format);
			writer.write(document);
			writer.close();

		} else {
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				Element el_user = (Element) iterator.next();
				// 遍历user一下的节点
				Iterator el_user_iterator = el_user.elementIterator();
				if (el_user_iterator.hasNext()) {
					Element el_userId = (Element) el_user_iterator.next();
					String simpleName = el_userId.getText();
					if (simpleName.equals(userLoginPage.getSimplifyName().toUpperCase())) {
						Element el_loginTime = (Element) el_user_iterator.next();
						el_loginTime.setText(userLoginPage.getRefreshTime());
						Element el_loginState = (Element) el_user_iterator.next();
						el_loginState.setText(userLoginPage.getLoginState());
						writer = new XMLWriter(new FileOutputStream(filePath), format);
						writer.write(document);
						writer.close();
					} else {
						continue;
					}
				}
			}
		}
		return;
	}

	/**
	 * 根据文件地址解析XML
	 * 
	 */
	public void parserXmlByFileName(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element Envelope = document.getRootElement();
			for (Iterator i = Envelope.elementIterator(); i.hasNext();) {
				Element body = (Element) i.next();
				//System.out.println(body.getName() + ":" + body.getText());
				for (Iterator j = body.elementIterator(); j.hasNext();) {
					Element loginResponse = (Element) j.next();
					//System.out.println(loginResponse.getName() + ":" + loginResponse.getText());
					for (Iterator k = loginResponse.elementIterator(); k.hasNext();) {
						Element loginResult = (Element) k.next();
						//System.out.println(loginResult.getName() + ":" + loginResult.getText());
					}

				}
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 在返回的内容中取出接口返回结果
	 * 
	 * @param XMLStr
	 *            webservice返回的全部内容
	 * @return Result 有用的结果
	 */
	public String getResResult(String XMLStr) {
		StringReader read = new StringReader(XMLStr);
		InputSource source = new InputSource(read);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(source);
			Element Envelope = document.getRootElement();
			for (Iterator i = Envelope.elementIterator(); i.hasNext();) {
				Element body = (Element) i.next();
				//System.out.println(body.getName() + ":" + body.getText());
				for (Iterator j = body.elementIterator(); j.hasNext();) {
					Element Response = (Element) j.next();
					//System.out.println(Response.getName() + ":" + Response.getText());
					for (Iterator k = Response.elementIterator(); k.hasNext();) {
						Element Result = (Element) k.next();
						//System.out.println(Result.getName() + ":" + Result.getText());
						return Result.getText();
					}

				}
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
			return "";
		}
		return "";
	}

	/**
	 * 根据ResponseResult获得webservice调用是否成功
	 * 
	 * @param resResult
	 *            返回的XML字符串
	 * @return isSuccess true or false
	 * 
	 */
	public boolean isSuccess(String resResult) {
		boolean issuccess = false;
		StringReader read = new StringReader(resResult);
		InputSource source = new InputSource(read);
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(source);
			Element result = document.getRootElement();
			Iterator iterator = result.elementIterator();
			if (iterator.hasNext()) {
				Element succeed = (Element) iterator.next();
				String succeedStr = succeed.getText();
				if (succeedStr.equals("true"))
					return true;
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issuccess;
	}

	/**
	 * 根绝resResult获取返回data
	 * 
	 * @param resResult
	 * @return data 失败为空
	 */
	public String getData(String resResult) {
		String data = "";
		StringReader read = new StringReader(resResult);
		InputSource source = new InputSource(read);
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(source);
			Element result = document.getRootElement();
			Iterator iterator = result.elementIterator();
			if (iterator.hasNext()) {
				Element succeed = (Element) iterator.next();
				//String succeedStr = succeed.getText();
				Element dataEle = (Element) iterator.next();
				data = dataEle.getText();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public String creatInspectionStationXMLStr(InspectionStation inspectionStation, SysUser sysUser) {
		// TODO Auto-generated method stub
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/监测站.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//filePath = "D:\\检测站.xml";
		//SAXReader sr = new SAXReader();
		//Document document;
		
		String path = "D:\\检测站.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				Element TS = (Element) iterator.next();
				Element TsNo = TS.element("TsNo");
				TsNo.setText(String.format("%03d", inspectionStation.getStationId()));
				Element TestStation = TS.element("TestStation");
				TestStation.setText(inspectionStation.getStationName());
				Element TestAddress = TS.element("TestAddress");
				TestAddress.setText(inspectionStation.getStationAddress());
				Element OrgType = TS.element("OrgType");
				OrgType.setText(inspectionStation.getQulificationType());
				Element AuthDate = TS.element("AuthDate");
				AuthDate.setText(inspectionStation.getQulificationTime().toString().substring(0, 10));
				Element RegDate = TS.element("RegDate");
				RegDate.setText(inspectionStation.getRegistrationTime().toString().substring(0, 10));
				Element ExpireDate = TS.element("ExpireDate");
				ExpireDate.setText(inspectionStation.getValidPeriod().toString().substring(0, 10));
				Element LegalPerson = TS.element("LegalPerson");
				LegalPerson.setText(sysUser.getUserName());
				Element Tel = TS.element("Tel");
				Tel.setText(sysUser.getTel());
				/*try {
					writer = new XMLWriter(new FileOutputStream(path), format);
					writer.write(document);
					writer.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}
	
	public String getSendFile(String fileToSend, String xmlMsg){
		String path = fileToSend;
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			document= reader.read(file);
			Element el_root = document.getRootElement();
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				 
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public String creatDetectionLineXMLStr(DetectionLine detectionLine) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/检测线.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\检测线.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				Element TS = (Element) iterator.next();
				Element TsNo = TS.element("TsNo");
				TsNo.setText(String.format("%03d", detectionLine.getInspectionStation().getStationId()));
				Element TestLineNo = TS.element("TestLineNo");
				TestLineNo.setText(detectionLine.getLineId()+"");
				Element TestType = TS.element("TestType");
				TestType.setText("A");
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	public String creatReferenceMaterialsXMLStr(ReferenceMaterialsRecord RMrecord) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/标准物质.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\标准物质.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				Element SM = (Element) iterator.next();
				Element TsNo = SM.element("TsNo");
				TsNo.setText(String.format("%03d",RMrecord.getInspectionStation().getStationId()));
				Element SMType = SM.element("SMType");
				SMType.setText(RMrecord.getMaterialName());
				Element Model = SM.element("Model");
				Model.setText(RMrecord.getSpecification());
				Element Provider = SM.element("Provider");
				Provider.setText(RMrecord.getManufacturer());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	public String creatUserXMLStr(SysUser sysUser) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/检测站人员.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\检测站人员.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			Iterator iterator = el_root.elementIterator();
			while (iterator.hasNext()) {
				Element SM = (Element) iterator.next();
				Element TsNo = SM.element("TsNo");
				TsNo.setText(String.format("%03d",sysUser.getInspectionStation().getStationId()));
				Element Name = SM.element("Name");
				Name.setText(sysUser.getUserName());
				Element Gender = SM.element("Gender");
				Gender.setText(sysUser.getSex());
				Element Age = SM.element("Age");
				Age.setText(38+"");
				Element Education = SM.element("Education");
				Education.setText(sysUser.getDegree());
				Element Major = SM.element("Major");
				Major.setText("");
				Element Position = SM.element("Position");
				Position.setText("检测员");
				Element PositionNo = SM.element("PositionNo");
				PositionNo.setText("");
				Element JobTitle = SM.element("JobTitle");
				JobTitle.setText(sysUser.getJobTitle());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	public String creatTestDataXMLStr(DetectionCommisionSheet detectionCommisionSheet) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/检验信息.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\检验信息.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element TestData = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			TestData = document.getRootElement();
			Iterator iterator = TestData.elementIterator();
			while (iterator.hasNext()) {
				Element Test = (Element) iterator.next();
				
				Test.element("License").setText(detectionCommisionSheet.getLicence());
				Test.element("LicenseType").setText(detectionCommisionSheet.getLicenseColor());
				Test.element("TestDate").setText(detectionCommisionSheet.getDetectionTime().toString());
				Test.element("AreaCode").setText("340202");
				Test.element("VehicleModel").setText(detectionCommisionSheet.getVehicleModelCode());
				Test.element("VehicleType").setText(detectionCommisionSheet.getVehicleType()+"");
				//TODO 使用性质
				Test.element("UseType").setText("A");
				Test.element("RegisterDate").setText(detectionCommisionSheet.getVehicleRegisterDate().toString());
				Test.element("Odometer").setText(detectionCommisionSheet.getTotalMiles()+"");
				Test.element("FuelType").setText(detectionCommisionSheet.getFuelType());
				
				Test.element("TestNo").setText(detectionCommisionSheet.getId()+"");
				Test.element("TestTimes").setText(detectionCommisionSheet.getYearCount()+"");
				Test.element("Result").setText(detectionCommisionSheet.getConclusion()+"");
				
				//TODO TestType
				if(detectionCommisionSheet.getTwoSpeedIdleMethod()!=null)
				{
					//双怠速法
					TwoSpeedIdleMethod twoSpeedIdelMethod = detectionCommisionSheet.getTwoSpeedIdleMethod();
					String twoSpeedIdelXMLStr = creatTwoSpeedIdelXMLStr(twoSpeedIdelMethod);
					Test.element("TestType").setText(twoSpeedIdelXMLStr);
					Test.element("TestLineNo").setText(twoSpeedIdelMethod.getDetectionLine().getLineId()+"");
					Test.element("TsNo").setText(twoSpeedIdelMethod.getDetectionLine().getInspectionStation().getStationId()+"");
				}else if(detectionCommisionSheet.getSteadyStateMethod()!=null)
				{
					//稳态工况法
					SteadyStateMethod steadyStateMethod = detectionCommisionSheet.getSteadyStateMethod();
					String steadyStateXMLStr = creatSteadyStateXMLStr(steadyStateMethod);
					Test.element("TestType").setText(steadyStateXMLStr);
					Test.element("TestLineNo").setText(steadyStateMethod.getDetectionLine().getLineId()+"");
					Test.element("TsNo").setText(steadyStateMethod.getDetectionLine().getInspectionStation().getStationId()+"");
				}else if(detectionCommisionSheet.getLugDownMethod()!=null)
				{
					//加载减速工况法
					LugDownMethod lugDownMethod = detectionCommisionSheet.getLugDownMethod();
					String lugDownXMLStr = creatlugDownXMLStr(lugDownMethod);
					Test.element("TestType").setText(lugDownXMLStr);
					Test.element("TestLineNo").setText(lugDownMethod.getDetectionLine().getLineId()+"");
					Test.element("TsNo").setText(lugDownMethod.getDetectionLine().getInspectionStation().getStationId()+"");
				}
				
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	private String creatlugDownXMLStr(LugDownMethod lugDownMethod) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/稳态.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\稳态.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			
			el_root.element("SmokeKLimit").setText("1");
			el_root.element("MaxPowerLimit").setText("90");
			el_root.element("RateRevUp").setText("4000");
			el_root.element("RateRevDown").setText("1000");
			el_root.element("K100").setText(lugDownMethod.getHundredPoint()+"");
			el_root.element("K90").setText(lugDownMethod.getNintyPoint()+"");
			el_root.element("K80").setText(lugDownMethod.getEightyPoint()+"");
			el_root.element("MaxPower").setText("60.16");
			el_root.element("Rev100").setText("2520");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	private String creatSteadyStateXMLStr(SteadyStateMethod steadyStateMethod) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/稳态.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\稳态.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			
			el_root.element("HC5025Limit").setText(steadyStateMethod.getWtHcAsm5025Limit()+"");
			el_root.element("CO5025Limit").setText(steadyStateMethod.getWtCoAsm5025Limit()+"");
			el_root.element("NO5025Limit").setText(steadyStateMethod.getWtNoAsm5025Limit()+"");
			el_root.element("CO2540Limit").setText(steadyStateMethod.getWtCoAsm2540Limit()+"");
			el_root.element("HC2540Limit").setText(steadyStateMethod.getWtHcAsm2540Limit()+"");
			el_root.element("NO2540Limit").setText(steadyStateMethod.getWtNoAsm2540Limit()+"");
			el_root.element("HC5025").setText(steadyStateMethod.getWtHcAsm5025()+"");
			el_root.element("CO5025").setText(steadyStateMethod.getWtCoAsm5025()+"");
			el_root.element("NO5025").setText(steadyStateMethod.getWtNoAsm5025()+"");
			el_root.element("CO2540").setText(steadyStateMethod.getWtCoAsm2540()+"");
			el_root.element("HC2540").setText(steadyStateMethod.getWtHcAsm2540()+"");
			el_root.element("NO2540").setText(steadyStateMethod.getWtNoAsm2540()+"");
			el_root.element("HC5025Judge").setText(steadyStateMethod.getWtHcAsm5025Judge()?"1":"0");
			el_root.element("CO5025Judge").setText(steadyStateMethod.getWtCoAsm5025Judge()?"1":"0");
			el_root.element("NO5025Judge").setText(steadyStateMethod.getWtNoAsm5025Judge()?"1":"0");
			el_root.element("CO2540Judge").setText(steadyStateMethod.getWtCoAsm2540Judge()?"1":"0");
			el_root.element("HC2540Judge").setText(steadyStateMethod.getWtHcAsm2540Judge()?"1":"0");
			el_root.element("NO2540Judge").setText(steadyStateMethod.getWtNoAsm2540Judge()?"1":"0");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	private String creatTwoSpeedIdelXMLStr(TwoSpeedIdleMethod twoSpeedIdelMethod) {
		String xmlPath = null;
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		//System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/双怠速.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "D:\\双怠速.xml";
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		Element el_root = null;
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			//document = sr.read(filePath);
			document= reader.read(file); 
			el_root = document.getRootElement();
			
			el_root.element("LowIdleCOLimit").setText(twoSpeedIdelMethod.getSdsLCoLimit()+"");
			el_root.element("LowIdleCO").setText(twoSpeedIdelMethod.getSdsLCo()+"");
			el_root.element("LowIdleCOJudge").setText((twoSpeedIdelMethod.getSdsLCoLimit()>twoSpeedIdelMethod.getSdsLCo())?"1":"0");
			el_root.element("LowIdleHCLimit").setText(twoSpeedIdelMethod.getSdsLHcLimit()+"");
			el_root.element("LowIdleHC").setText(twoSpeedIdelMethod.getSdsLHc()+"");
			el_root.element("LowIdleHCJudge").setText((twoSpeedIdelMethod.getSdsLHcLimit()>twoSpeedIdelMethod.getSdsLHc())?"1":"0");
			el_root.element("HighIdleCOLimit").setText(twoSpeedIdelMethod.getSdsHCoLimit()+"");
			el_root.element("HighIdleCO").setText(twoSpeedIdelMethod.getSdsHCo()+"");
			el_root.element("HighIdleCOJudge").setText((twoSpeedIdelMethod.getSdsHCoLimit()>twoSpeedIdelMethod.getSdsHCo())?"1":"0");
			el_root.element("HighIdleHCLimit").setText(twoSpeedIdelMethod.getSdsHHcLimit()+"");
			el_root.element("HighIdleHC").setText(twoSpeedIdelMethod.getSdsHHc()+"");
			el_root.element("HighIdleHCJudge").setText((twoSpeedIdelMethod.getSdsHHcLimit()>twoSpeedIdelMethod.getSdsHHc())?"1":"0");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String allStr = document.asXML();
		//String result = allStr.substring(allStr.indexOf("TSData")-1);
		return document.asXML();
	}

	
	
	
}
