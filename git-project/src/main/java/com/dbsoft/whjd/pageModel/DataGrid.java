package com.dbsoft.whjd.pageModel;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {
	
	private Long total;// 总记录数
	private List rows = new ArrayList();

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
