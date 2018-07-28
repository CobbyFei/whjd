package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestFreeAccelerationMethodService {
	@Autowired
	private IFreeAccelerationMethodService methodService;

	@Test
	public void testGetAllDetections() {
		FreeAccelerationMethodPage page = new FreeAccelerationMethodPage();
		page.setPage(1);
		page.setRows(10);
		DataGrid dg = methodService.getAllDetections(page);
		System.out.println(dg.getTotal());
		System.out.println(((FreeAccelerationMethodPage) dg.getRows().get(0))
				.getLineId());
	}

	@Test
	public void testUpdateMethod() throws Exception {
		FreeAccelerationMethodPage page = new FreeAccelerationMethodPage();
		page.setId(2);
		page.setIntHasEmissionProcessDevice(1);
		page.setMethodId(1);
		page.setLineId(1);
		page.setConclusion(2);
		page.setPage(1);
		page.setRows(10);
		page.setLastOneTest(1.0);
		page.setLastTwoTest(2.0);
		page.setLastThreeTest(3.0);
		page.setRpm(324.0);
		methodService.updateMethod(page);
		FreeAccelerationMethodPage p = new FreeAccelerationMethodPage();
		p.setPage(1);
		p.setRows(10);
		DataGrid dg = methodService.getAllDetections(p);
		System.out.println(dg.getTotal());
		System.out.println(((FreeAccelerationMethodPage) dg.getRows().get(0))
				.getLineId());
	}

	@Test
	public void testGetDetectionById() {
		Integer id = 2;
		FreeAccelerationMethodPage page = methodService.getDetectionById(id);
		System.out.println(page.getAccessorName());
	}
}
