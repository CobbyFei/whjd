package com.dbsoft.whjd.pageModel;

import java.io.Serializable;
import java.util.List;

import com.dbsoft.whjd.model.RolePrivilege;

/**
 * JSON模型
 * 前后台的交互一般通过这个类来完成
 * 
 * @author 周建群
 * 
 */
public class Json_RolePrivilege implements Serializable {

	private boolean success = false;// 是否成功
	private String msg = "";		// 提示信息
	private Object obj = null;		// 其他信息
	private List<String> ids;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}


}
