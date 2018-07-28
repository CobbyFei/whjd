package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.MaintainceRecordPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestMaintainceRecordService {
	@Autowired
	private IMaintainceRecordService recordService;

	@Test
	public void testSaveMaintainceRecord() throws Exception {
		MaintainceRecordPage page = new MaintainceRecordPage();
		page.setLineId(2);
		page.setDeviceName("hallo");
		page.setIntIsNormal(0);
		page.setStrMaintainceTime("2014-05-13");
		recordService.saveMaintainceRecord(page);
	}

	@Test
	public void testGetAllMaintainceRecord() throws Exception {
		MaintainceRecordPage page = new MaintainceRecordPage();
		page.setPage(1);
		page.setRows(10);
		DataGrid dg = recordService.getAllMaintainceRecord(page);
		System.out.println(dg.getTotal());
		System.out.println(((MaintainceRecordPage) dg.getRows().get(0))
				.getIntIsNormal());
	}

	@Test
	public void testDeleteMaintainceRecord() {
		MaintainceRecordPage page = new MaintainceRecordPage();
		page.setLineId(2);
		page.setDeviceName("hallo");
		page.setIntIsNormal(0);
		page.setIds("1");
		page.setStrMaintainceTime("2014-05-13");
		recordService.deleteMaintainceRecord(page);
	}
}
