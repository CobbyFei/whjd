package com.dbsoft.whjd.service;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dbsoft.whjd.model.DevicePurchaseRecord;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DevicePurchaseRecordPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IDevicePurchaseRecordService {
	 public DataGrid getDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage);
	 public List<DevicePurchaseRecord> datagrid(int page, int rows);
	 public Json addDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage);
	 public Json updateDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage);	 
	 public Json deleteDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage);
     public Json exportDevicePurchaseRecord(DevicePurchaseRecordPage devicePurchaseRecordPage);
}
