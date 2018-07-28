package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;

public interface ILimitValueReferenceService {

	public DataGrid getLimitValues(LimitValueReferencePage referencePage);

	public Json saveLimitValue(LimitValueReferencePage referencePage);

	public Json deleteLimitValue(LimitValueReferencePage referencePage);

	public Json editLimitValue(LimitValueReferencePage referencePage);

}
