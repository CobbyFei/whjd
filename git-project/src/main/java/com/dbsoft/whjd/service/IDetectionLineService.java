package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.action.DetectionLineAction;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionLinePage;
import com.dbsoft.whjd.pageModel.Json;

public interface IDetectionLineService {
	/**
	 * 添加检测线信息
	 * @param detectionLinePage
	 * @return
	 */
	public Json addDetectionLine(DetectionLinePage detectionLinePage);
	/**
	 * 检测检测线名字是否存在
	 * @param detectionLinePage
	 * @return
	 */
	public Json hasLineName(DetectionLinePage detectionLinePage);
	/**
	 * 检测检测线站内编号是否存在
	 * @param detectionLinePage
	 * @return
	 */
	public Json hasLocaleId(DetectionLinePage detectionLinePage);
	/**
	 * 获取所有的检测线信息
	 * @param detectionLinePage
	 * @return
	 */
	public DataGrid getAllDetectionLine(DetectionLinePage detectionLinePage);
	/**
	 * 注销检测线，支持批量进行操作
	 * @param detectionLinePage
	 * @return
	 */
	public Json cancelDetectionLine(DetectionLinePage detectionLinePage);
	
	/**
	 * 从数据库中删除检测线，用做扩展功能
	 * @param detectionLinePage
	 * @return
	 */
	public Json deleteDetectionLine(DetectionLinePage detectionLinePage);
	/**
	 * 修改检测线的信息
	 */
	public Json modifyDetectionLine(DetectionLinePage detectionLinePage);
	
	/**
	 * 查找检测线的名称
	 */
	public List<DetectionLinePage> getDetectionLineName(DetectionLinePage detectionLinePage);

	/**
	 * 导出检测线的信息
	 */
	public Json exportDetectionLine(DetectionLinePage detectionLinePage);
	/**
	 * 查找管理员
	 * @param detectionLine
	 * @return
	 */
	public SysUser getStationManager(DetectionLinePage detectionLinePage);
}
