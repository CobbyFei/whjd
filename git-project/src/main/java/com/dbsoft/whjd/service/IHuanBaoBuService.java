package com.dbsoft.whjd.service;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DetectionLinePage;

public interface IHuanBaoBuService {
	String getLoginData();
	
	void addInspectionStation(InspectionStation inspectionStation, SysUser sysUser);

	void addDetectionLine(DetectionLine detectionLine);

	void addReferenceMaterials(ReferenceMaterialsRecord record);

	void addUser(SysUser sysUser);
	
	public void addTestData(DetectionCommisionSheet detectionCommisionSheet);
}
