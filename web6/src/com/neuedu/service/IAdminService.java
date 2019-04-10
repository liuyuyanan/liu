package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.nueedu.pojo.Admin;

public interface IAdminService {
	public ServerResponse<Admin> login(String username,String password);
	public boolean isusernameexists(String username,String password);
	public ServerResponse<Admin> register(String username,String password);
}
