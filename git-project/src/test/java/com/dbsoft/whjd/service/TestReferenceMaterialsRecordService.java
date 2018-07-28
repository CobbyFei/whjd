package com.dbsoft.whjd.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestReferenceMaterialsRecordService {
	@Autowired
	private IReferenceMaterialsRecordService recordService;

	@Test
	public void saveReferenceMaterialsRecordTest() {
		ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
		page.setStationName("t1");
		page.setManufacturer("miao");
		page.setMaterialName("mi");
		page.setPurchaseNum(34);
		page.setStrPurchaseTime("2014-04-23");
		recordService.saveReferenceMaterialsRecord(page);
	}

	@Test
	public void getAllReferenceMaterialsRecordTest() throws Exception {
		ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
		page.setPage(1);
		page.setRows(10);
		page.setStationName("t1");
		// page.setMaterialName("d");
		 page.setBeginTime("2014-05-06");
		 page.setStatus(0);
		// page.setEndTime("2014-05-14");
		DataGrid dg = recordService.getAllReferenceMaterialsRecord(page);
		System.out.println(dg.getTotal());
	}

	@Test
	public void getMaterialNamesTest() throws Exception {
		ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
		page.setQ("k");
		List<ReferenceMaterialsRecordPage> list = recordService
				.getMaterialNames(page);
		for (ReferenceMaterialsRecordPage p : list) {
			System.out.println(p.getMaterialName());
		}
	}
}
