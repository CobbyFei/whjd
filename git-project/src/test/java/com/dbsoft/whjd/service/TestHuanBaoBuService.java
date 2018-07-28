package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.util.XMLUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestHuanBaoBuService {
	@Autowired
	private IHuanBaoBuService huanBaoBuService;
	@Autowired
	private IInspectionStationService inspectionStationService;
	
	@Autowired
	private IDetectionLineService detectionLineservice;

	/*@Test
	public void testGetAllDetections() {
		//System.out.println(huanBaoBuService.getLoginData());
		//DetectionLinePage detectionLinePage = new DetectionLinePage();
		//XMLUtils.getInstance().creatDetectionLineXML(detectionLinePage);
		InspectionStation inspectionStation = new InspectionStation();
		SysUser sysUser = new SysUser();
		inspectionStation.setStationId(1);
		inspectionStation.setStationName("测试");
		inspectionStation.setStationAddress("地址");
		huanBaoBuService.addInspectionStation(inspectionStation, sysUser);
	
		InspectionStationPage inspectionStationPage =  new InspectionStationPage();
		inspectionStationPage.setDirectorId(6);
		inspectionStationPage.setStationAddress("地址");
		inspectionStationPage.setStationName("测试名字");
		inspectionStationPage.setStationId(1);
		inspectionStationPage.setQulificationType("A");
		inspectionStationPage.setQulificationTime("2016-04-01");
		inspectionStationPage.setRegistrationTime("2016-04-01");
		inspectionStationPage.setValidPeriod("2016-04-01");
		
		inspectionStationService.addInspectionStation(inspectionStationPage);
	
	}*/
	@Test
	public void testAddDetectionLine() {
		DetectionLinePage detectionLinePage = new DetectionLinePage();
		detectionLinePage.setLineId(12);
		//InspectionStation inspectionStation = new InspectionStation();
		//inspectionStation.setStationId(11);
		detectionLinePage.setStationId(11);
		detectionLinePage.setLineName("测试哈");
		detectionLineservice.addDetectionLine(detectionLinePage);
	}
	
}
