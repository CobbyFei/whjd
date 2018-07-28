package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DeviceInfoPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IDeviceInfoService {
	public Json saveDeviceInfo(DeviceInfoPage deviceInfoPage);

	public void cancelDeviceInfo(DeviceInfoPage deviceInfoPage);

	public DataGrid getAllDeviceInfo(DeviceInfoPage deviceInfoPage)
			throws Exception;

	public Json editDeviceInfo(DeviceInfoPage deviceInfoPage);

	public List<DeviceInfoPage> getDeviceModels(DeviceInfoPage deviceInfoPage)
			throws Exception;

	public List<DeviceInfoPage> getDeviceNames(DeviceInfoPage deviceInfoPage)
			throws Exception;

	public List<DeviceInfoPage> getManufacturers(DeviceInfoPage deviceInfoPage)
			throws Exception;

	public List<DeviceInfoPage> getDeviceInfo(DeviceInfoPage deviceInfoPage);

	public Json exportDeviceInfo(DeviceInfoPage deviceInfoPage)
			throws Exception;
}
