package com.dbsoft.whjd.service;

import java.security.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.util.ChangeTimeFormat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestManageSysUserService {
	@Autowired
	private IManageUserService manageUserService;

	@Test
	public void addNewUser(){
		SysUserPage sysUserPage = new SysUserPage();
		sysUserPage.setUserName("莫璇");
		sysUserPage.setSimplifyName("MXKKK");
		sysUserPage.setSex("男");
		sysUserPage.setStationId(1);
		try {
			manageUserService.addNewUser(sysUserPage);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
