package com.dbsoft.whjd.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.MenuPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestDetectionLineService {
	@Autowired
	private IDetectionLineService iDetectionLineService;
	
	@Test
	public void testAddDetectionLine() {
		DetectionLinePage detectionLinePage=new DetectionLinePage();
		detectionLinePage.setLineName("第一个检测站");
		detectionLinePage.setLineStatus(0);
		detectionLinePage.setLocaleId(1);
		detectionLinePage.setMaxDetectionNum(15);
		detectionLinePage.setStationId(1);
		iDetectionLineService.addDetectionLine(detectionLinePage);
	}
}
