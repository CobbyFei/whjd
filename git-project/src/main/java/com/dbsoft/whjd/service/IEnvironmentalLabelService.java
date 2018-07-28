package com.dbsoft.whjd.service;

import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IEnvironmentalLabelService {
	public DataGrid getEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage);
	 //public List<EnvironmentalLabelCollar> getInspectionStationName(String q);
	 public Integer addEnvironmentalLabel(DetectionCommisionSheet sheet);
	 public Json updateEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage);
	 public Json cancelEnvironmentalLabel(EnvironmentalLabelPage environmentalLabelPage);
     public EnvironmentalLabelPage getEnvironmentalLabelById(Integer environmentalLabelId);
     public Json setIsPrint(Integer environmentalLabelId);
}
