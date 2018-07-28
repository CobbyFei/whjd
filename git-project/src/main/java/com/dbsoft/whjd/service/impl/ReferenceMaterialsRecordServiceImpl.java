package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DeviceInfo;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DeviceInfoPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.service.IReferenceMaterialsRecordService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "referenceMaterialsRecordService")
public class ReferenceMaterialsRecordServiceImpl implements
		IReferenceMaterialsRecordService {

	private IBaseDao<ReferenceMaterialsRecord> recordDao;
	private IBaseDao<InspectionStation> stationDao;
	private IHuanBaoBuService huanBaoBuService;
		
		

	public IHuanBaoBuService getHuanBaoBuService() {
		return huanBaoBuService;
	}
	@Autowired
	public void setHuanBaoBuService(IHuanBaoBuService huanBaoBuService) {
		this.huanBaoBuService = huanBaoBuService;
	}
	public IBaseDao<ReferenceMaterialsRecord> getRecordDao() {
		return recordDao;
	}

	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}

	@Resource(name = "baseDao")
	public void setRecordDao(IBaseDao<ReferenceMaterialsRecord> recordDao) {
		this.recordDao = recordDao;
	}

	public void copyToRecord(ReferenceMaterialsRecordPage page,
			ReferenceMaterialsRecord record) {
		BeanUtils.copyProperties(page, record);
		record.setPurchaseTime(ChangeTimeFormat.getInstance().strToTimeStamp(
				page.getStrPurchaseTime()));
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println(page.getStationName());
		params.put("stationName", page.getStationName());
		InspectionStation station = stationDao
				.get("FROM InspectionStation as s WHERE s.stationName= :stationName",
						params);
		record.setInspectionStation(station);
	}

	@Override
	public void saveReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) {
		ReferenceMaterialsRecord record;
		if (recordPage.getRecordId() == null) {
			record = new ReferenceMaterialsRecord();
			copyToRecord(recordPage, record);
			record.setIsPush(0);
			recordDao.save(record);
			//huanBaoBuService.addReferenceMaterials(record);
		} else {
			record = recordDao.get(ReferenceMaterialsRecord.class,
					recordPage.getRecordId());
			copyToRecord(recordPage, record);
			recordDao.update(record);
		}
	}

	@Override
	public DataGrid getAllReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) throws Exception {
		DataGrid dg = new DataGrid();
		recordPage = (ReferenceMaterialsRecordPage) TrimString.getInstance()
				.trimObjectString(recordPage);
		List<ReferenceMaterialsRecordPage> recordPageList = new ArrayList<ReferenceMaterialsRecordPage>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "FROM ReferenceMaterialsRecord as r WHERE 1=1";
		if (recordPage.getStationName() != null
				&& !recordPage.getStationName().trim().equals("")) {
			params.put("stationName", recordPage.getStationName());
			hql += " AND r.inspectionStation.stationName= :stationName";
		}
		if (recordPage.getMaterialName() != null) {
			params.put("materialName", "%" + recordPage.getMaterialName() + "%");
			hql += " and r.materialName like :materialName";
		}
		if (recordPage.getManufacturer() != null) {
			params.put("manufacturer", "%" + recordPage.getManufacturer() + "%");
			hql += " and r.manufacturer like :manufacturer";
		}
		if (recordPage.getSpecification() != null) {
			params.put("specification", "%" + recordPage.getSpecification()
					+ "%");
			hql += " and r.specification like :specification";
		}
		if (recordPage.getBeginTime() != null
				&& !recordPage.getBeginTime().equals("")) {
			params.put("beginTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(recordPage.getBeginTime()));
			hql += " and r.purchaseTime>= :beginTime";
		}
		if (recordPage.getEndTime() != null
				&& !recordPage.getEndTime().equals("")) {
			params.put("endTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(recordPage.getEndTime()));
			hql += " and r.purchaseTime<= :endTime";
		}
		if (recordPage.getStatus() != null && recordPage.getStatus() != 2) {
			params.put("status", recordPage.getStatus());
			hql += " AND r.status= :status";
		}
		List<ReferenceMaterialsRecord> recordList = recordDao.find(hql, params,
				recordPage.getPage(), recordPage.getRows());
		dg.setTotal(recordDao.count("SELECT COUNT(*)" + hql, params));
		for (ReferenceMaterialsRecord record : recordList) {
			ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
			BeanUtils.copyProperties(record, page);
			page.setStrPurchaseTime(ChangeTimeFormat.getInstance()
					.timeStampToString(record.getPurchaseTime()));
			page.setStationName(record.getInspectionStation().getStationName());
			recordPageList.add(page);
		}
		dg.setRows(recordPageList);
		return dg;
	}

	@Override
	public void deleteReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) {
		if (recordPage.getIds() != null) {
			for (String id : recordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					ReferenceMaterialsRecord record = recordDao
							.get(ReferenceMaterialsRecord.class,
									Integer.valueOf(id));
					if (record != null) {
						record.setStatus(1);
						recordDao.update(record);
					}
				}
			}
		}
	}

	@Override
	public List<ReferenceMaterialsRecordPage> getMaterialNames(
			ReferenceMaterialsRecordPage recordPage) {
		String q;
		if (recordPage.getQ() == null || recordPage.getQ().trim().equals(""))
			q = "";
		else
			q = recordPage.getQ();
		List<ReferenceMaterialsRecord> recordList = new ArrayList<ReferenceMaterialsRecord>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT r.materialName FROM ReferenceMaterialsRecord as r where r.materialName like :text";
		if (recordPage.getStationName() != null
				&& !recordPage.getStationName().trim().equals("")) {
			tMap.put("stationName", recordPage.getStationName());
			hql += " AND r.inspectionStation.stationName= :stationName";
		}
		// 其实按照SQL语句，返回的应该是字符串列表；按照定义，返回的应该是采购记录对象。
		// 但是实际是：本质为字符串的List被认作了采购记录对象的List，可是里面的东西还是字符串。
		// 最终结果就是从一个采购记录对象的List里面拿出一堆字符串。
		// ╮(╯▽╰)╭
		recordList = recordDao.find(hql, tMap, 1, 10);
		List<ReferenceMaterialsRecordPage> pageList = new ArrayList<ReferenceMaterialsRecordPage>();
		for (Object record : recordList) {
			ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
			page.setMaterialName(record.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public List<ReferenceMaterialsRecordPage> getManufacturers(
			ReferenceMaterialsRecordPage recordPage) {
		String q;
		if (recordPage.getQ() == null || recordPage.getQ().trim().equals(""))
			q = "";
		else
			q = recordPage.getQ();
		List<ReferenceMaterialsRecord> recordList = new ArrayList<ReferenceMaterialsRecord>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT r.manufacturer FROM ReferenceMaterialsRecord as r where r.manufacturer like :text";
		if (recordPage.getStationName() != null
				&& !recordPage.getStationName().trim().equals("")) {
			tMap.put("stationName", recordPage.getStationName());
			hql += " AND r.inspectionStation.stationName= :stationName";
		}
		if (recordPage.getMaterialName() != null
				&& !recordPage.getMaterialName().trim().equals("")) {
			tMap.put("materialName", "%" + recordPage.getMaterialName() + "%");
			hql += " AND r.materialName like :materialName";
		}
		recordList = recordDao.find(hql, tMap, 1, 10);
		List<ReferenceMaterialsRecordPage> pageList = new ArrayList<ReferenceMaterialsRecordPage>();
		for (Object record : recordList) {
			ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
			page.setManufacturer(record.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public List<ReferenceMaterialsRecordPage> getModels(
			ReferenceMaterialsRecordPage recordPage) {
		String q;
		if (recordPage.getQ() == null || recordPage.getQ().trim().equals(""))
			q = "";
		else
			q = recordPage.getQ();
		List<ReferenceMaterialsRecord> recordList = new ArrayList<ReferenceMaterialsRecord>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT r.model FROM ReferenceMaterialsRecord as r where r.model like :text";
		if (recordPage.getStationName() != null
				&& !recordPage.getStationName().trim().equals("")) {
			tMap.put("stationName", recordPage.getStationName());
			hql += " AND r.inspectionStation.stationName= :stationName";
		}
		if (recordPage.getMaterialName() != null
				&& !recordPage.getMaterialName().trim().equals("")) {
			tMap.put("materialName", "%" + recordPage.getMaterialName() + "%");
			hql += " AND r.materialName like :materialName";
		}
		recordList = recordDao.find(hql, tMap, 1, 10);
		List<ReferenceMaterialsRecordPage> pageList = new ArrayList<ReferenceMaterialsRecordPage>();
		for (Object record : recordList) {
			ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
			page.setSpecification(record.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public Json exportReferenceMaterialsRecord(
			ReferenceMaterialsRecordPage recordPage) throws Exception {
		List<Object> pageList = new ArrayList<Object>();
		if (recordPage.getIds() != null) {
			for (String id : recordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					ReferenceMaterialsRecord record = recordDao
							.get(ReferenceMaterialsRecord.class,
									Integer.valueOf(id));
					if (record != null) {
						ReferenceMaterialsRecordPage page = new ReferenceMaterialsRecordPage();
						BeanUtils.copyProperties(record, page);
						page.setStrPurchaseTime(ChangeTimeFormat.getInstance()
								.timeStampToString(record.getPurchaseTime()));
						page.setStationName(record.getInspectionStation()
								.getStationName());
						pageList.add(page);
					}
				}
			}
		}
		String filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
				"formatExcel", "formatTitle",
				ReferenceMaterialsRecordServiceImpl.class);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		return retJson;
	}

	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((ReferenceMaterialsRecordPage) obj).getRecordId()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getStationName()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getMaterialName()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getSpecification()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getManufacturer()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getPurchaseNum()
				.toString());
		result.add(((ReferenceMaterialsRecordPage) obj).getStrPurchaseTime()
				.toString());
		if (((ReferenceMaterialsRecordPage) obj).getStatus() == 0)
			result.add("正常");
		else
			result.add("注销");
		result.add(((ReferenceMaterialsRecordPage) obj).getRemarks().toString());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("所属检测站");
		result.add("标准物质名称");
		result.add("型号规格");
		result.add("制造厂商");
		result.add("采购数量");
		result.add("采购时间");
		result.add("状态");
		result.add("备注");
		return result;
	}
}
