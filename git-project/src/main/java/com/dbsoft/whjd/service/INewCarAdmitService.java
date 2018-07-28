package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.NewCarAdmitSheetPage;

public interface INewCarAdmitService {

	DataGrid getNewCarAdmitSheet(NewCarAdmitSheetPage newCarAdmitSheetPage) throws Exception;

	Json changeNewCarAdmitStatus(NewCarAdmitSheetPage newCarAdmitSheetPage);

	NewCarAdmitSheetPage gerNewCarAdmitSheetById(Integer id);

}
