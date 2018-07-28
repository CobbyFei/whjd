package com.dbsoft.whjd.service;

import java.util.List;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IInspectionStationService {
	 public DataGrid getInspectionStation(InspectionStationPage inspectionStationPage);
	 public List<InspectionStationPage> getInspectionStationName(String q);
	 public Json addInspectionStation(InspectionStationPage inspectionStationPage);
	 public Json updateInspectionStation(InspectionStationPage inspectionStationPage);
	 public Json cancelInspectionStation(InspectionStationPage inspectionStationPage);
	 public Json deleteInspectionStation(InspectionStationPage inspectionStationPage);
     public Json hasInspectionStation(String q);
	 //public List<InspectionStationPage> getInspectionStationValidPeriod(String q);
}
