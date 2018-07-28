package com.dbsoft.whjd.service;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.Json;

public interface IEnvironmentalLabelCollarService {
	public DataGrid getEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
	 //public List<EnvironmentalLabelCollar> getInspectionStationName(String q);
	 public Json addEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
	 public Json updateEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
	 public Json cancelEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
     public Json exportEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
     public DataGrid getLeaveLabel(EnvironmentalLabelCollarPage environmentalLabelCollarPage);
}
