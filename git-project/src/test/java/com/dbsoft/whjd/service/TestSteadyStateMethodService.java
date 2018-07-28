package com.dbsoft.whjd.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.SteadyStateMethodPage;
import com.dbsoft.whjd.pageModel.TrainingRecordPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestSteadyStateMethodService {
	@Autowired
	private ISteadyStateMethodService iStateMethodService;
	
	@Test
	public void findCommisionSheetsOfSteadyStateMethod(){
		
		SteadyStateMethodPage steadyStateMethodPage = new SteadyStateMethodPage();
		
	//	steadyStateMethodPage.setReportNumber("201405181534098");
		
		DataGrid dataGrid =iStateMethodService.findCommisionSheetsOfSteadyStateMethod(steadyStateMethodPage);
		List<SteadyStateMethodPage> ls= dataGrid.getRows();
		
	//	System.out.println(((SteadyStateMethodPage)ls.get(0)).getRecordId());
		
	}
}
