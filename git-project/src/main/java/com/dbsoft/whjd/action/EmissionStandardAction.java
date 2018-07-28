package com.dbsoft.whjd.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.VehicleTypeInfoPage;
import com.dbsoft.whjd.service.IEmissionStandardService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="emissionStandardAction" , results = {
		@Result(name = "addVehicleInfo", location = "/jsp/NewCarAdmit/addVehicleInfo.jsp"),
		@Result(name = "addUsingVehicleInfo", location = "/jsp/UsingCarAdmit/addVehicleInfo.jsp"),
		@Result(name="edit",location="/jsp/NewCarAdmit/editVehicleType.jsp")  //2017-12-19添加
		}
)
public class EmissionStandardAction  extends BaseAction implements ModelDriven<VehicleTypeInfoPage>{
	
	private static final String EDIT_VEHICLETYPEINFO="edit";//2017-12-19添加

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
		
		String ZRBZ = emissionStandardService.getZRBZ(vehicleTypeInfoPage);
		System.out.println("该类型车辆的准入标准为  国" + ZRBZ);
		if(ZRBZ==null||ZRBZ.equals("")){
			json.setSuccess(false);
			json.setMsg("查询车辆准入标准失败，请检查燃油类型，车辆总质量，车辆用途等信息。");
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
		
		//2018-2-23
		//String ZRBZ = "4";
		//修改在用车转入的准入标准，原来统一都是国四，现改为：汽油车为国一及以上，柴油车为国三及以上，2018-01-02修改
		String ZRBZ = emissionStandardService.getUsingCarZRBZ(vehicleTypeInfoPage);
		System.out.println("该类型在用车辆的准入标准为  国" + ZRBZ);

		if(Integer.parseInt(PF)<Integer.parseInt(ZRBZ)){
			json.setSuccess(false);
			json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",车辆准入排放标准为:国"+ZRBZ+"，该车不符合条件，不予准入。");
			
			super.writeJson(json);
			return;
		}
		
		String vechileIssueCertTime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			
		json.setSuccess(true);
		json.setMsg("根据车型库查新车辆排放标准为：国"+PF+",车辆准入排放标准为:国"+ZRBZ+"，该车符合条件，请录入车辆详细信息后打印在用车辆环保准入证明。");
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
    
	public String addVehicleInfo(){
		return "addVehicleInfo";
	}	
	public String addUsingVehicleInfo(){
		return "addUsingVehicleInfo";
	}
	
    /**
     * 获取车型信息，修改于2017-12-13
     */
    public void getAllVehicleType(){
    	DataGrid dg=new DataGrid();
    	try{
    		dg=emissionStandardService.getAllVehicleType(vehicleTypeInfoPage);
    	}catch (Exception e) {
    		System.out.println("获取数据出现错误");
		}
    	super.writeJson(dg);
    }
    
	/**
	  * 返回车型信息修改页面，2017-12-19添加
	  */
	public String edit(){
		return EDIT_VEHICLETYPEINFO;
	}
	
	/**
	  * 修改车型信息，2017-12-19添加
	  */
	public void modifyVehicleType(){
	  Json res=new Json();
	  try {
	       res=emissionStandardService.modifyVehicleType(vehicleTypeInfoPage);
          } catch (Exception e) {
		   res.setMsg("修改出现错误");
		   res.setSuccess(false);
		  }
	  super.writeJson(res);
	}
	
	/**
	  * 删除车型信息，2017-12-20添加
	  */
	public void deleteVehicleType(){
		Json j = new Json();
		try {
			j = emissionStandardService.deleteVehicleType(vehicleTypeInfoPage);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setMsg("删除失败");
		}
		super.writeJson(j);
	}
	@Override
	public VehicleTypeInfoPage getModel() {
		// TODO Auto-generated method stub
		return vehicleTypeInfoPage;
	}
	
	
	
}
