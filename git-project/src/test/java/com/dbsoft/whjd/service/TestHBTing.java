package com.dbsoft.whjd.service;

import java.io.IOException;
import java.text.ParseException;

import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.util.SOAPService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml", "classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional

public class TestHBTing {
	
	private IHBTingSynService ser;
	
	/*@Test
	public void testUpload(){
		SOAPService ser = new SOAPService();
		JSONObject json=new JSONObject(); 
		JSONArray JGJG = new JSONArray();
		JSONObject oneJGJG=new JSONObject(); 
		try {
			oneJGJG.put("GLKS", "省厅机动车检测站");
			oneJGJG.put("JGMC", "芜湖测试");
			oneJGJG.put("JGFZR", "张三");
			oneJGJG.put("JGLX", "测试");
			oneJGJG.put("CLRQ", "2016-10-10");
			oneJGJG.put("LXR", " 测试");
			JGJG.put(oneJGJG);
			json.put("H_JGJG_JBXX", JGJG);  
			JSONObject res_json = ser.HBTingUpload("JG001",json);
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			System.out.println("==================>"+res.get("code")+res.get("message"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public IHBTingSynService getSer() {
		return ser;
	}
	@Autowired
	public void setSer(IHBTingSynService ser) {
		this.ser = ser;
	}

	/*@Test
	public void testUpload(){
		SOAPService ser = new SOAPService();
		JSONObject json=new JSONObject(); 
		JSONArray JGJG = new JSONArray();
		JSONObject oneJGJG=new JSONObject(); 
		try {
			oneJGJG.put("CLXH", "NJ1041DMS");
			oneJGJG.put("FDJXH", "4BC2(4102Q)");
			JGJG.put(oneJGJG);
			json.put("QUERYCONDITION", JGJG);  
			JSONObject res_json = ser.HBTingDownload("CX001",json);
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONArray T_VEHICLES = (JSONArray) res_json.get("T_VEHICLES");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			System.out.println("==================>"+res.get("code")+res.get("message"));
			System.out.println(T_VEHICLES.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
//	@Test
//	public void testUploadBlackName(){
//		try {
//			ser.uploadStation();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/*String str = "{\"SYSTEM\":[{\"code\":\"0\",\"message\":\"数据上传成功\"}]}";
//		try {
//			JSONObject resJson = new JSONObject(str);
//			JSONArray res_json_arr = (JSONArray) resJson.get("SYSTEM");
//			JSONObject res = (JSONObject) res_json_arr.get(0);
//			if (res.get("code").equals("0")) {
//				System.out.print("========321654321");
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}*/
//		
//	}
	@Test
	public void testUploadVehicleInfo(){
		try {
			ser.uploadVehicleInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
