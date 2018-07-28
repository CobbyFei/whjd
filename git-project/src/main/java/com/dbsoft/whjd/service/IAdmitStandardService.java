package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EmissionAdmitStandardPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IAdmitStandardService {

	DataGrid findAdmitStandards(EmissionAdmitStandardPage emissionAdmitStandardPage);

	Json updateAdmitStandard(EmissionAdmitStandardPage emissionAdmitStandardPage);

	Json addAdmitStandard(EmissionAdmitStandardPage emissionAdmitStandardPage);

	Json deleteAdmitStandardValue(EmissionAdmitStandardPage emissionAdmitStandardPage);

}
