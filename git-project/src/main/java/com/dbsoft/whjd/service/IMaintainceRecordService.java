package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MaintainceRecordPage;

public interface IMaintainceRecordService {

	DataGrid getAllMaintainceRecord(MaintainceRecordPage recordPage) throws Exception;

	void saveMaintainceRecord(MaintainceRecordPage recordPage) throws Exception;

	void deleteMaintainceRecord(MaintainceRecordPage recordPage);

	Json exportDeviceInfo(MaintainceRecordPage recordPage) throws Exception;

}
