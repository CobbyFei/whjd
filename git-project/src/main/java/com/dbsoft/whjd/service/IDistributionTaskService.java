package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;

import com.dbsoft.whjd.pageModel.Json;

public interface IDistributionTaskService {
     public Json addDistributionTask(DetectionCommisionSheetPage detectionCommisionSheetPage);
     public Json reDistributionTask(DetectionCommisionSheetPage detectionCommisionSheetPage);
}
