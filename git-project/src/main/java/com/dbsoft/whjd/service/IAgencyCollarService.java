package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.AgencyEnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.util.ExceptionUtil;

public interface IAgencyCollarService {
	
	/**
	 * 向数据库中添加市局里的标志领用记录
	 * @param agencyEnvironmentalLabelCollarPage
	 * @return
	 */
	public Json addAgencyCollarRecord(AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
   
	/**
    * 获取所有的市局环保标志领用记录或者根据搜索条件进行搜索
    * @param agencyEnvironmentalLabelCollarPage
    * @return
    */
	public DataGrid getAllAgencyCollarRecord(AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
	/**
	 * 注销选中的环保标志领用记录
	 * @param agencyEnvironmentalLabelCollarPage
	 * @return
	 */
	public Json cancelAgencyCollarRecord(AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
    /**
     * 删除环保标志领用记录
     * @param agencyEnvironmentalLabelCollarPage
     * @return
     */
	public Json deleteAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
    /**
     * 修改选中的市局标志领用记录
     * @param agencyEnvironmentalLabelCollarPage
     * @return
     */
	public Json modifyAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
   /**
    * 统计总共领用的环保记录
    * @param agencyEnvironmentalLabelCollarPage
    * @return
    */
	public DataGrid getSumOfLabel(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage);
}
