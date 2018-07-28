package com.dbsoft.whjd.service;

import java.text.ParseException;

import org.json.JSONException;

public interface IHBTingSynService {
	public void uploadStation() throws JSONException;
	public void uploadSysUser() throws JSONException;
	public void uploadDeteLine() throws JSONException;
	public void uploadVehicleInfo() throws  Exception;
	public void uploadBlackName() throws JSONException;
	public void uploadReferenceMaterials() throws JSONException;
	public void uploadDectectionBase() throws Exception;
	public void uploadSteadyStateMethodResult() throws Exception;
	public void uploadSteadyStateMethodProcess() throws JSONException;
	public void uploadTwoSpeedIdleMethodResult() throws  Exception;
	public void uploadTwoSpeedIdleMethodProcess() throws JSONException;
	public void uploadLugDownMethodResult() throws  Exception;
	public void uploadLugDownMethodProcess() throws JSONException;
	
}
