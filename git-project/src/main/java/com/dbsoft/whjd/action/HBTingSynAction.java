package com.dbsoft.whjd.action;

import org.apache.struts2.convention.annotation.Action;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.service.IHBTingSynService;

@Action(value = "hBTingSynAction")
public class HBTingSynAction extends BaseAction{
	private IHBTingSynService hBTingSynService;

	public IHBTingSynService gethBTingSynService() {
		return hBTingSynService;
	}
	@Autowired
	public void sethBTingSynService(IHBTingSynService hBTingSynService) {
		this.hBTingSynService = hBTingSynService;
	}
	
	public void uploadBlackName(){
		try {
			hBTingSynService.uploadBlackName();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadStation(){
		try {
			hBTingSynService.uploadStation();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadSysUser(){
		try {
			hBTingSynService.uploadSysUser();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadDeteLine(){
		try {
			hBTingSynService.uploadDeteLine();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadVehicleInfo(){
			try {
				hBTingSynService.uploadVehicleInfo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void uploadReferenceMaterials(){
		try {
			hBTingSynService.uploadReferenceMaterials();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadDectectionBase(){
		try {
			hBTingSynService.uploadDectectionBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadSteadyStateMethodResult(){
		try {
			hBTingSynService.uploadSteadyStateMethodResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadSteadyStateMethodProcess(){
		try {
			hBTingSynService.uploadSteadyStateMethodProcess();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadTwoSpeedIdleMethodResult(){
		try {
			hBTingSynService.uploadTwoSpeedIdleMethodResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadTwoSpeedIdleMethodProcess(){
		try {
			hBTingSynService.uploadTwoSpeedIdleMethodProcess();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadLugDownMethodResult(){
		try {
			hBTingSynService.uploadLugDownMethodResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadLugDownMethodProcess(){
		try {
			hBTingSynService.uploadLugDownMethodProcess();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
