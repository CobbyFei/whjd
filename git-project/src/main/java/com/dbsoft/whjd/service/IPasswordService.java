package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.PasswordPage;

public interface IPasswordService {
	public Json resetPassword(PasswordPage passwordPage);
	public Json changePassword(PasswordPage passwordPage);
}
