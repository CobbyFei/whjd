package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.LimitValueReferencePage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestLimitValueReferenceService {
	@Autowired
	private ILimitValueReferenceService referenceService;

	@Test
	public void testGetReferenceValues() {
		LimitValueReferencePage page = new LimitValueReferencePage();
		page.setDetectionMedhodName("自由加速法");
		page.setRows(10);
		page.setPage(1);
		System.out.println(referenceService.getLimitValues(page).getTotal());
	}

	@Test
	public void testSaveZJReference() {
		LimitValueReferencePage page = new LimitValueReferencePage();
		page.setDetectionMethodId(3);
		page.setZjLimit((double) 14);
	}

	@Test
	public void testUpdate() {
		LimitValueReferencePage page = new LimitValueReferencePage();
		page.setDetectionMethodId(3);
		page.setZjLimit((double) 14);
		LimitValueReferencePage p1 = new LimitValueReferencePage();
		p1.setDetectionMethodId(3);
		p1.setZjLimit((double) 20);
		LimitValueReferencePage p2 = new LimitValueReferencePage();
		p2.setDetectionMethodId(3);
	}
}
