package com.dbsoft.whjd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.service.IRepairService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-hibernate.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class TestRepairService {

	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	
	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}
	
	@Autowired
	private IRepairService repairService;

	@Test
	public void testRepairMenu() {
		//repairService.repair();
		String update_commisionSheet_hql = "from DetectionCommisionSheet as dcs where dcs.licence=:vin ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vin", "LJ11KBAC5C6017036");
		List<DetectionCommisionSheet> list2 = detectionCommisionSheetDao.find(update_commisionSheet_hql, paramMap);
		System.out.println(list2);
	
	}
}
