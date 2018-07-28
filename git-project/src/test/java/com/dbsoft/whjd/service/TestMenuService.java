package com.dbsoft.whjd.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.MenuPage;
import com.dbsoft.whjd.service.IMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class TestMenuService {
	
	@Autowired
	private IMenuService menuService;
	
	@Test
	public void testGetAllTreeNode() {
		List<MenuPage> munus = menuService.getAllTreeNode();
		System.out.println(munus.size());
	}
	
}
