package com.dbsoft.whjd.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLDecoder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.util.SOAPService;
import com.dbsoft.whjd.util.XMLUtils;

@Service(value = "huanBaoBuService")
public class HuanBaoBuServiceImppl implements IHuanBaoBuService {

	IBaseDao<InspectionStation> inspectionStationDao;
	IBaseDao<DetectionLine> detectionLineDao;
	IBaseDao<ReferenceMaterialsRecord> referenceMaterialsRecordDao;
	IBaseDao<SysUser> sysUserDao;
	IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	
	
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Autowired
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	public IBaseDao<ReferenceMaterialsRecord> getReferenceMaterialsRecordDao() {
		return referenceMaterialsRecordDao;
	}
	@Autowired
	public void setReferenceMaterialsRecordDao(IBaseDao<ReferenceMaterialsRecord> referenceMaterialsRecordDao) {
		this.referenceMaterialsRecordDao = referenceMaterialsRecordDao;
	}
	public IBaseDao<DetectionLine> getDetectionLineDao() {
		return detectionLineDao;
	}
	@Autowired
	public void setDetectionLineDao(IBaseDao<DetectionLine> detectionLineDao) {
		this.detectionLineDao = detectionLineDao;
	}
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	@Autowired
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}

	@Override
	public String getLoginData() {
		SOAPService soapService = new SOAPService();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/ WSVHJ /login";
		//String xml = test.class.getClassLoader().getResource("login.xml").getFile();
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		System.out.println(basePath);
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "hbbXML/login.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(filePath);
		File fileToSend = new File("D:\\login.xml");
		try {
			String responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
				return XMLUtils.getInstance().getData(resultStr);
			else 
				return "接口调用失败";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void addInspectionStation(InspectionStation inspectionStation, SysUser sysUser) {
		SOAPService soapService = new SOAPService();
		String xmlStr = XMLUtils.getInstance().creatInspectionStationXMLStr(inspectionStation, sysUser);
		String path = "D:\\sendTSData.xml";
		File fileToSend = new File(path);
		String key = getLoginData();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/WSVHJ/ sendTSData";
		String responseXmlStr;
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		try {
			document= reader.read(fileToSend);
			Element el_root = document.getRootElement();
			Element el_body = el_root.element("Body");
			Element el_TSData = el_body.element("sendTSData");
			Element el_key = el_TSData.element("key");
			el_key.setText(key);
			Element el_strData = el_TSData.element("strTSData");
			el_strData.setText(xmlStr);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			System.out.println("接口返回结果====>"+XMLUtils.getInstance().isSuccess(resultStr)+XMLUtils.getInstance().getData(resultStr));
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
			{
				inspectionStation.setIsPush(1);
				inspectionStationDao.update(inspectionStation);
			}
			else 
				System.out.println("上传监测站失败");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addDetectionLine(DetectionLine detectionLine) {
		String xmlStr = XMLUtils.getInstance().creatDetectionLineXMLStr(detectionLine);
		String path = "D:\\sendTSLData.xml";
		File fileToSend = new File(path);
		String key = getLoginData();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/WSVHJ/sendTSLData";
		String responseXmlStr;
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		SOAPService soapService = new SOAPService();
		
		
		try {
			document= reader.read(fileToSend);
			Element el_root = document.getRootElement();
			Element el_body = el_root.element("Body");
			Element el_TSLData = el_body.element("sendTSLData");
			Element el_key = el_TSLData.element("key");
			el_key.setText(key);
			Element el_strData = el_TSLData.element("strTSLData");
			el_strData.setText(xmlStr);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			System.out.println("接口返回结果====>"+XMLUtils.getInstance().isSuccess(resultStr)+XMLUtils.getInstance().getData(resultStr));
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
			{
				detectionLine.setIsPush(1);
				detectionLineDao.update(detectionLine);
			}
			else 
				System.out.println("上传检测线失败");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addReferenceMaterials(ReferenceMaterialsRecord record) {
		String xmlStr = XMLUtils.getInstance().creatReferenceMaterialsXMLStr(record);
		String path = "D:\\sendTMData.xml";
		File fileToSend = new File(path);
		String key = getLoginData();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/WSVHJ/sendTMData";
		String responseXmlStr;
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		SOAPService soapService = new SOAPService();
		
		
		try {
			document= reader.read(fileToSend);
			Element el_root = document.getRootElement();
			Element el_body = el_root.element("Body");
			Element el_TMData = el_body.element("sendTMData");
			Element el_key = el_TMData.element("key");
			el_key.setText(key);
			Element el_strData = el_TMData.element("strTMData");
			el_strData.setText(xmlStr);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			System.out.println("接口返回结果====>"+XMLUtils.getInstance().isSuccess(resultStr)+XMLUtils.getInstance().getData(resultStr));
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
			{
				record.setIsPush(1);
				referenceMaterialsRecordDao.update(record);
			}
			else 
				System.out.println("上传标准物质失败");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addUser(SysUser sysUser) {
		String xmlStr = XMLUtils.getInstance().creatUserXMLStr(sysUser);
		String path = "D:\\sendTSMData.xml";
		File fileToSend = new File(path);
		String key = getLoginData();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/WSVHJ/sendTSMData";
		String responseXmlStr;
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		SOAPService soapService = new SOAPService();
		
		
		try {
			document= reader.read(fileToSend);
			Element el_root = document.getRootElement();
			Element el_body = el_root.element("Body");
			Element el_TSMData = el_body.element("sendTSMData");
			Element el_key = el_TSMData.element("key");
			el_key.setText(key);
			Element el_strData = el_TSMData.element("strTSMData");
			el_strData.setText(xmlStr);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			System.out.println("接口返回结果====>"+XMLUtils.getInstance().isSuccess(resultStr)+XMLUtils.getInstance().getData(resultStr));
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
			{
				sysUser.setIsPush(1);
				sysUserDao.update(sysUser);
			}
			else 
				System.out.println("上传监测站人员失败");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void addTestData(DetectionCommisionSheet detectionCommisionSheet) {
		String xmlStr = XMLUtils.getInstance().creatTestDataXMLStr(detectionCommisionSheet);
		String path = "D:\\sendTestData.xml";
		File fileToSend = new File(path);
		String key = getLoginData();
		String urlString = "http://webhj.vecc-mep.org.cn/WSVHJ/WSVHJ.asmx";
		String soapActionString = "http://webhj.vecc-mep.org.cn/WSVHJ/sendTestData";
		String responseXmlStr;
		SAXReader reader = new SAXReader();
		Document document = null; 
		XMLWriter writer;
		OutputFormat format = OutputFormat.createPrettyPrint();
		SOAPService soapService = new SOAPService();
		
		
		try {
			document= reader.read(fileToSend);
			Element el_root = document.getRootElement();
			Element el_body = el_root.element("Body");
			Element el_TSMData = el_body.element("sendTestData ");
			Element el_key = el_TSMData.element("key");
			el_key.setText(key);
			Element el_strData = el_TSMData.element("strTestData");
			el_strData.setText(xmlStr);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseXmlStr = soapService.getResult(urlString, soapActionString, fileToSend);
			String resultStr = XMLUtils.getInstance().getResResult(responseXmlStr);
			System.out.println("接口返回结果====>"+XMLUtils.getInstance().isSuccess(resultStr)+XMLUtils.getInstance().getData(resultStr));
			if(XMLUtils.getInstance().isSuccess(resultStr)==true)
			{
				detectionCommisionSheet.setIsPush(1);
				detectionCommisionSheetDao.update(detectionCommisionSheet);
			}
			else 
				System.out.println("上传检测信息失败");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
