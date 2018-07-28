package com.dbsoft.whjd.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.InspectionStationPage;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestInspectionStation {
	@Autowired
	private IInspectionStationService inspectionStationService;

	@Test
	public void testAddInspectionStation() {
		InspectionStationPage inspectionStationPage=new InspectionStationPage();
		inspectionStationPage.setStationName("sdf");
		inspectionStationPage.setQulificationTime("2014-05-01");
		inspectionStationPage.setQulificationType("A");
		inspectionStationPage.setRegistrationTime("2014-05-01");
		inspectionStationPage.setStationAddress("南京");
		inspectionStationPage.setRemarks("sdaf士大夫");
		inspectionStationPage.setValidPeriod("2014-05-01");
		inspectionStationPage.setDirectorId(1);
		inspectionStationService.addInspectionStation(inspectionStationPage);
	}
/*	@Test
	public void testDeleteInspectionStation(){
		InspectionStationPage inspectionStationPage=new InspectionStationPage();
		inspectionStationPage.setIds("sdf,wer");
		inspectionStationService.deleteInspectionStation(inspectionStationPage);		
	}*/
/*	@Test
	public void testModifyInspectionStation() {
		InspectionStationPage inspectionStationPage=new InspectionStationPage();
		inspectionStationPage.setStationId(3);
		inspectionStationPage.setStationName("南京");
		inspectionStationPage.setQulificationTime("2014-05-01");
		inspectionStationPage.setQulificationType("A");
		inspectionStationPage.setRegistrationTime("2014-05-01");
		inspectionStationPage.setStationAddress("南京");
		inspectionStationPage.setRemarks("222222");
		inspectionStationPage.setValidPeriod("2014-05-01");
		inspectionStationPage.setDirectorId(2);
		inspectionStationService.updateInspectionStation(inspectionStationPage);
	}*/
}
