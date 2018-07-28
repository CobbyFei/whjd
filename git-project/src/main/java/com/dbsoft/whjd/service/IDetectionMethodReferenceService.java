package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionMethodReferencePage;
import com.dbsoft.whjd.pageModel.Json;

public interface IDetectionMethodReferenceService {
	/**
	 * 添加检测方法的记录
	 * @param detectionMethodReferencePage
	 * @return
	 */
	public Json addDetectionMethodReference(DetectionMethodReferencePage detectionMethodReferencePage);
	
   /**
    * 删除检测方法的记录
    * @param detectionMethodReferencePage
    * @return
    */
	public Json deleteDetecitonMethodReference(DetectionMethodReferencePage detectionMethodReferencePage);
   
	/**
    * 获取所有的检测方法参照记录
    * @param detectionMethodReferencePage
    * @return
    */
	public DataGrid getAllDetectionMethodReference(DetectionMethodReferencePage detectionMethodReferencePage);
    
    /**
     * 修改检测方法对应的参照记录
     * @param detectionMethodReferencePage
     * @return
     */
	public Json modifyDetectionMethodReference(DetectionMethodReferencePage detectionMethodReferencePage);
    
	/**
	 * 为了产生联想
	 * @return
	 */
	public List<DetectionMethodReferencePage> getAllDetectionMethodReferenceList(DetectionMethodReferencePage detectionMethodReferencePage);
	 
	/**
	 * 判断方法名称是否存在
	 * @param detectionMethodReferencePage
	 * @return
	 */
	public Json hasMethodName(DetectionMethodReferencePage detectionMethodReferencePage);
	/**
	 * 返回检测方法
	 * @param detectionMethodReferencePage
	 * @return
	 */
	public Json getDetectionMethod(DetectionMethodReferencePage detectionMethodReferencePage);
}
