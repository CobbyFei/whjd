package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;

public interface IReferenceMaterialsRecordService {
	public void saveReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage);

	public void deleteReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage);

	public DataGrid getAllReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) throws Exception;

	public List<ReferenceMaterialsRecordPage> getMaterialNames(
			ReferenceMaterialsRecordPage recordPage);

	public List<ReferenceMaterialsRecordPage> getManufacturers(
			ReferenceMaterialsRecordPage recordPage);

	public List<ReferenceMaterialsRecordPage> getModels(
			ReferenceMaterialsRecordPage recordPage);

	public Json exportReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) throws Exception;
}
