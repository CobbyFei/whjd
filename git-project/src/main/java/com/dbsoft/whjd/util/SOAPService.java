package com.dbsoft.whjd.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;

public class SOAPService {
	public String getResult(String urlString, String soapActionString, File fileToSend)
			throws IOException, FileNotFoundException, MalformedURLException, ProtocolException, Exception {
		byte[] buf = new byte[(int) fileToSend.length()];
		new FileInputStream(fileToSend).read(buf);
		URL url = new URL(urlString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("soapActionString", soapActionString);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		out.write(buf);
		out.close();
		byte[] datas = readInputStream(httpConn.getInputStream());
		String result = new String(datas);
		// 打印返回结果
		return result;
	}

	private static String urlStr = "http://18.8.0.1:8080/HBService.asmx";
	private static String khdm = "340200";
	private static String jkxlh = "EE27ED204BE656819689993831990774";

	/**
	 * 环保厅数据上传
	 * 
	 * @throws IOException
	 * @throws DocumentException 
	 * 
	 */
	public JSONObject HBTingUpload(String ywlb, JSONObject json) throws IOException, DocumentException {
		JSONObject resJson = null;
		URL url = new URL(urlStr);
		String downloadInfoStr = json.toString();
		
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "/hbtXML/upload.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO
		//String filePath = "D:\\HBServer\\whjd\\target\\classes\\hbtXML\\upload.xml";
		SAXReader sr = new SAXReader();
		Document document = sr.read(filePath);
		Element el_root = document.getRootElement();
		Iterator iterator = el_root.elementIterator();
		Element body = (Element) iterator.next();
		Iterator bodyIterator = body.elementIterator();
		Element UpLoadData = (Element) bodyIterator.next();
		Iterator paramIterator = UpLoadData.elementIterator();
		while (paramIterator.hasNext()) {
			Element khdmParam = (Element) paramIterator.next();
			khdmParam.setText(khdm);
			Element jkxlhParam = (Element) paramIterator.next();
			jkxlhParam.setText(jkxlh);
			Element ywlbParam = (Element) paramIterator.next();
			ywlbParam.setText(ywlb);
			Element uploadInfoParam = (Element) paramIterator.next();
			uploadInfoParam.setText(downloadInfoStr);
		}
		
		String xmlStr = document.asXML();

		System.out.println("正在上传"+ywlb+"数据:"+xmlStr);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(xmlStr.length()));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", "http://hbservice.hbweb.com/UpLoadData");
		httpConn.setRequestMethod("POST");
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);// 如果通过post提交数据，必须设置允许对外输出数据
		httpConn.setUseCaches(false);// 忽略缓存 
		
		
		/*// string to inputstream
		InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());
		// to byte[]
		byte[] data;
		data = new byte[inputStream.available()];
		inputStream.read(data);*/
		
		byte[] requestStringBytes = xmlStr.getBytes("UTF-8");  
		OutputStream outputStream = httpConn.getOutputStream();   
		outputStream.write(requestStringBytes);   
		outputStream.close();   
		

		if (httpConn.getResponseCode() == 200) {
			System.out.println("连接成功");
			// 请求返回的数据
			InputStream in = httpConn.getInputStream();
			String a = null;
			try {
				byte[] data1 = new byte[in.available()];
				
				in.read(data1);
				// 转成字符串
				//a = new String(data1);
				a = new String(data1, "utf-8");
				System.out.println("返回的结果为："+a);
				
				
				//string to xml
				Document doc = DocumentHelper.parseText(a);
				
				/*StringReader stringReader = new StringReader(a);   		  
				InputSource is = new InputSource(stringReader);   		  
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   		  
				DocumentBuilder builder=factory.newDocumentBuilder();    		  
				org.w3c.dom.Document doc = builder.parse(is);*/
				
				Element res_root = doc.getRootElement();
				Iterator res_root_iterator = res_root.elementIterator();
				Element res_body = (Element) res_root_iterator.next();
				Iterator res_body_iterator = res_body.elementIterator();
				Element rep_data = (Element) res_body_iterator.next();
				Iterator rep_data_iterator = rep_data.elementIterator();
				Element upload_info = (Element) rep_data_iterator.next();
				String jsonStr = upload_info.getText();
				
				//string to json
				resJson = new JSONObject(jsonStr);
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			System.out.println("连接失败");
		}

		return resJson;

	}
	
	/**
	 * 环保厅数据下载
	 * 
	 * @throws IOException
	 * @throws DocumentException 
	 * 
	 */
	public JSONObject HBTingDownload(String ywlb, JSONObject json) throws IOException, DocumentException {
		JSONObject resJson = null;
		URL url = new URL(urlStr);
		String downloadInfoStr = json.toString();
		
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(basePath, "utf-8") + "/hbtXML/upload.xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO
		//String filePath = "D:\\HBServer\\whjd\\target\\classes\\hbtXML\\download.xml";
		SAXReader sr = new SAXReader();
		Document document = sr.read(filePath);
		Element el_root = document.getRootElement();
		Iterator iterator = el_root.elementIterator();
		Element body = (Element) iterator.next();
		Iterator bodyIterator = body.elementIterator();
		Element UpLoadData = (Element) bodyIterator.next();
		Iterator paramIterator = UpLoadData.elementIterator();
		while (paramIterator.hasNext()) {
			Element khdmParam = (Element) paramIterator.next();
			khdmParam.setText(khdm);
			Element jkxlhParam = (Element) paramIterator.next();
			jkxlhParam.setText(jkxlh);
			Element ywlbParam = (Element) paramIterator.next();
			ywlbParam.setText(ywlb);
			Element uploadInfoParam = (Element) paramIterator.next();
			uploadInfoParam.setText(downloadInfoStr);
		}
		
		String xmlStr = document.asXML();

		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(xmlStr.length()));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", "http://hbservice.hbweb.com/DownLoadData");
		httpConn.setRequestMethod("POST");
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);// 如果通过post提交数据，必须设置允许对外输出数据
		httpConn.setUseCaches(false);// 忽略缓存 
		
		
		/*// string to inputstream
		InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());
		// to byte[]
		byte[] data;
		data = new byte[inputStream.available()];
		inputStream.read(data);*/
		
		byte[] requestStringBytes = xmlStr.getBytes("UTF-8");  
		OutputStream outputStream = httpConn.getOutputStream();   
		outputStream.write(requestStringBytes);   
		outputStream.close();   
		

		if (httpConn.getResponseCode() == 200) {
			System.out.println("yes++");
			// 请求返回的数据
			InputStream in = httpConn.getInputStream();
			String a = null;
			try {
				byte[] data1 = new byte[in.available()];
				in.read(data1);
				// 转成字符串
				a = new String(data1);
				System.out.println(a);
				
				
				//string to xml
				Document doc = DocumentHelper.parseText(a);
				
				/*StringReader stringReader = new StringReader(a);   		  
				InputSource is = new InputSource(stringReader);   		  
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   		  
				DocumentBuilder builder=factory.newDocumentBuilder();    		  
				org.w3c.dom.Document doc = builder.parse(is);*/
				
				Element res_root = doc.getRootElement();
				Iterator res_root_iterator = res_root.elementIterator();
				Element res_body = (Element) res_root_iterator.next();
				Iterator res_body_iterator = res_body.elementIterator();
				Element rep_data = (Element) res_body_iterator.next();
				Iterator rep_data_iterator = rep_data.elementIterator();
				Element upload_info = (Element) rep_data_iterator.next();
				String jsonStr = upload_info.getText();
				
				//string to json
				resJson = new JSONObject(jsonStr);
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			System.out.println("no++");
		}

		return resJson;

	}
	

	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}
}
