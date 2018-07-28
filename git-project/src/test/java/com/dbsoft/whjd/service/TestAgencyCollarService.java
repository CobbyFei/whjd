package com.dbsoft.whjd.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.AgencyEnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestAgencyCollarService {
	@Autowired
	private IAgencyCollarService agencyCollarService;

	@Test
	public void testAddInspectionStation() {
		AgencyEnvironmentalLabelCollarPage  agencyEnvironmentalLabelCollarPage=new AgencyEnvironmentalLabelCollarPage();
		agencyEnvironmentalLabelCollarPage.setPage(1);
		agencyEnvironmentalLabelCollarPage.setRows(10);
		agencyCollarService.getSumOfLabel(agencyEnvironmentalLabelCollarPage);
	}

}
