package com.dbsoft.whjd.service;
import java.util.List;

import com.dbsoft.whjd.pageModel.BlackNameListPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;

/**
 * 
 * @author wzr
 *
 */
public interface IVehicleLimitRecordService {
	/**
	 * 添加违规记录
	 * @param blackNameListPage
	 * @return
	 */
    public Json addVehicleLimitRecord(BlackNameListPage blackNameListPage);
    /**
     * 取得所有的车辆限行记录
     */
    public DataGrid getAllVechileLimitRecord(BlackNameListPage blackNameListPage);
   
   /**
    * 注销车辆限行记录
    * @param blackNameListPage
    * @return
    */
    public Json cancelVehicleLimitRecord(BlackNameListPage blackNameListPage);
    /**
     * 修改车辆限行记录信息
     * @param blackNameListPage
     * @return
     */
    public Json modifyVehicleLimitRecord(BlackNameListPage blackNameListPage);
    /**
     * 从数据库中删除车辆限行记录
     * @param blackNameListPage
     * @return
     */
    public Json deleteVehicleLimitRecord(BlackNameListPage blackNameListPage);
    /**
     * 判断是否存在违章车辆
     * @param blackNameListPage
     * @return
     */
    public Json isBlacKNameExists(BlackNameListPage blackNameListPage);
    
    /**
     * 导出车辆违规记录
     */
    public Json exportVehicleLimit(BlackNameListPage blackNameListPage);
    /**
     * 从外部的excel导入违章记录
     * @param blackNameListPage
     * @return Json
     */
    public Json importVehicleLimit(BlackNameListPage blackNameListPage);
    /**
     * 将临时文件转存为excel文件
     * @param blackNameListPage
     * @return
     */
    public Json copyToExcel(BlackNameListPage blackNameListPage);
    
}
