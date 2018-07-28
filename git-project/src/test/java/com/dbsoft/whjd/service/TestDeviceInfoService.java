package com.dbsoft.whjd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.pageModel.DeviceInfoPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestDeviceInfoService {
	@Autowired
	private IDeviceInfoService deviceInfoService;

	@Test
	public void testSaveDeviceInfo() {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setDeviceModel("dfs");
		page.setDeviceName("dwre");
		page.setDeviceStatus(0);
		page.setLineId(1);
		page.setManufacturer("hello");
		page.setRemarks("sliehrfls");
		page.setStationName("t1");
		DeviceInfoPage page1 = new DeviceInfoPage();
		page1.setDeviceModel("dfs");
		page1.setDeviceName("dwre");
		page1.setDeviceStatus(0);
		page1.setLineId(1);
		page1.setManufacturer("hello");
		page1.setRemarks("sliehrfls");
		page1.setStationName("t1");
		deviceInfoService.saveDeviceInfo(page);
		deviceInfoService.saveDeviceInfo(page1);
	}

	@Test
	public void testCancelDeviceInfo() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setId(1);
		page.setDeviceModel("dfs");
		page.setDeviceName("dwre");
		page.setDeviceStatus(0);
		page.setLineId(1);
		page.setManufacturer("hello");
		page.setRemarks("sliehrfls");
		page.setStationName("t1");
		deviceInfoService.cancelDeviceInfo(page);
		page.setDeviceStatus(1);
		page.setRows(10);
		page.setPage(1);
		System.out.println(deviceInfoService.getAllDeviceInfo(page).getTotal());
	}

	@Test
	public void testGetAllDeviceInfo() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setStationName("t1");
		page.setDeviceStatus(1);
		page.setLineId(1);
		page.setPage(1);
		page.setRows(10);
		deviceInfoService.getAllDeviceInfo(page);
		System.out.println(deviceInfoService.getAllDeviceInfo(page).getTotal());
	}

	@Test
	public void testEditDeviceInfo() {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setStationName("t1");
		page.setLineName("second");
		deviceInfoService.editDeviceInfo(page);
	}

	@Test
	public void testGetDeviceNames() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setQ("");
		deviceInfoService.getDeviceNames(page);
	}

	@Test
	public void testGetDeviceModels() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setQ("");
		page.setDeviceName("hello");
		deviceInfoService.getDeviceModels(page);
	}

	@Test
	public void testGetManufacturers() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setQ("");
		deviceInfoService.getManufacturers(page);
	}

	@Test
	public void testExportDeviceInfo() throws Exception {
		DeviceInfoPage page = new DeviceInfoPage();
		page.setIds("1");
		deviceInfoService.exportDeviceInfo(page);
	}
}
