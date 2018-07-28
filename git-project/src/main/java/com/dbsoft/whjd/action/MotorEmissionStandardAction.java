package com.dbsoft.whjd.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.VehicleTypeInfoPage;
import com.dbsoft.whjd.service.IEmissionStandardService;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="MotorEmissionStandardAction" , results = {
		@Result(name = "addMotorVehicleInfo", location = "/jsp/NewCarAdmit/addMotorVehicleInfo.jsp"),
		@Result(name = "addUsingVehicleInfo", location = "/jsp/UsingCarAdmit/addVehicleInfo.jsp")
		}
)
public class MotorEmissionStandardAction  extends BaseAction implements ModelDriven<VehicleTypeInfoPage>{

	private VehicleTypeInfoPage vehicleTypeInfoPage = new VehicleTypeInfoPage();
	private DetectionCommisionSheetPage detectionCommisionSheetPage = new DetectionCommisionSheetPage();
	
	private IEmissionStandardService emissionStandardService;
	
	public IEmissionStandardService getEmissionStandardService() {
		return emissionStandardService;
	}
	@Autowired
	public void setEmissionStandardService(IEmissionStandardService emissionStandardService) {
		this.emissionStandardService = emissionStandardService;
	}
	public void judgeAdmit(){
		Json json = new Json();
		String PF = emissionStandardService.getPF(vehicleTypeInfoPage);
		System.out.println("车辆排放标准为  国"+PF);
		if(PF==null||PF.equals("")){
			json.setSuccess(false);
			json.setMsg("查询车辆排放标准失败，请检查车辆型号以及发动机型号等信息。");
			super.writeJson(json);
			return;
		}
		
		String ZRBZ = emissionStandardService.getMotorZRBZ(vehicleTypeInfoPage);
		System.out.println("该类型车辆的准入标准为  国" + ZRBZ);
		if(ZRBZ==null||ZRBZ.equals("")){
			json.setSuccess(false);
			json.setMsg("查询车辆准入标准失败，请检查车辆生产日期。");
			super.writeJson(json);
			return;
		}
		
		if(Integer.parseInt(PF)<Integer.parseInt(ZRBZ)){
			json.setSuccess(false);
			json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",该类型车辆准入排放标准为:国"+ZRBZ+"，该车不符合条件，不予准入。");
			super.writeJson(json);
			return;
		}
		
		String vechileIssueCertTime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			
		json.setSuccess(true);
		json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",该类型车辆准入排放标准为:国"+ZRBZ+"，该车符合条件，请录入车辆详细信息后打印新注册车辆环保准入证明。");
		json.setObj(vehicleTypeInfoPage.toJSONString()
				+" \"PF\" : \""+PF+"\" , \"ZRBZ\" : \""+ZRBZ+"\" , \"vechileIssueCertTime\" :\""
				+vechileIssueCertTime+"\"}");
		super.writeJson(json);
	}
	
	public void judgeUsingCarAdmit(){
		Json json = new Json();
		String PF = emissionStandardService.getPF(vehicleTypeInfoPage);
		System.out.println("车辆排放标准为  国"+PF);
		if(PF==null||PF.equals("")){
			json.setSuccess(false);
			json.setMsg("查询车辆排放标准失败，请检查车辆型号以及发动机型号等信息。");
			super.writeJson(json);
			return;
		}
		
		String ZRBZ = "4";

		if(Integer.parseInt(PF)<Integer.parseInt(ZRBZ)){
			json.setSuccess(false);
			json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",车辆准入排放标准为:国"+ZRBZ+"，该车不符合条件，不予准入。");
			super.writeJson(json);
			return;
		}
		
		String vechileIssueCertTime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			
		json.setSuccess(true);
		json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",车辆准入排放标准为:国"+ZRBZ+"，该车符合条件，请录入车辆详细信息后打印新注册车辆环保准入证明。");
		json.setObj(vehicleTypeInfoPage.toJSONString()
				+" \"PF\" : \""+PF+"\" , \"ZRBZ\" : \""+ZRBZ+"\" , \"vechileIssueCertTime\" :\""
				+vechileIssueCertTime+"\"}");
		super.writeJson(json);
	}
	
	public void addVehicletype(){
		Json json = new Json();
		
		try {
			json = emissionStandardService.addVehicletype(vehicleTypeInfoPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.setSuccess(false);
			json.setMsg("添加成功");
		}
		
		super.writeJson(json);
	}
	
	public String addMotorVehicleInfo(){
		return "addMotorVehicleInfo";
	}
	
	public String addUsingVehicleInfo(){
		return "addUsingVehicleInfo";
	}
	
	@Override
	public VehicleTypeInfoPage getModel() {
		// TODO Auto-generated method stub
		return vehicleTypeInfoPage;
	}
	
	
	
}
