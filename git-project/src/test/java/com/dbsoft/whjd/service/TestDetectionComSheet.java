package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestDetectionComSheet {
	@Autowired
	private IEmissionStandardService emissionService;
	@Test
	public void testDetComSheet(){
		
		DetectionCommisionSheetPage detectionCommisionSheetPage = new DetectionCommisionSheetPage();
		
		detectionCommisionSheetPage.setVehicleModelCode("SQR7163T117");
		detectionCommisionSheetPage.setEngineModel("SQRE4G16");
		detectionCommisionSheetPage.setVehicleRegisterDate("2012-07-27");
		
		//detectionCommisionSheetPage.setMaxTotalQuality(4500.00);
		
		//emissionService.addDetectionRecord(detectionCommisionSheetPage);
	}
}
