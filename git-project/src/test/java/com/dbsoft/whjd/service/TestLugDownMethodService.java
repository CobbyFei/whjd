package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.LugDownMethodPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestLugDownMethodService {
	@Autowired
	private ILugDownMethodService methodService;

	@Test
	public void testGetAllDetections() {
		LugDownMethodPage page = new LugDownMethodPage();
		page.setPage(1);
		page.setRows(10);
		DataGrid dg = methodService.getAllDetections(page);
		System.out.println(dg.getTotal());
	}

}
