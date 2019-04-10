package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.controller.AdminServlet;
import com.neuedu.service.IAdminService;
import com.nueedu.pojo.Admin;


import sun.security.jca.GetInstance;
public class AdminServiceImpl implements IAdminService{
	private static AdminServiceImpl instance;
	//List<Admin> admins=new ArrayList<Admin>();
	Map<String, Admin> admins=new HashMap<String,Admin>(); 
	private AdminServiceImpl(){
		Admin admin1=new Admin("zhangsan","1111");
		admins.put(admin1.getUsername(),admin1);
		//this.admins.add(admin1);
	}
	public static synchronized AdminServiceImpl GetInstance() {
		if(instance==null) {
			instance=new AdminServiceImpl();
		}
		return instance;
	}
	@Override
	public ServerResponse login(String username, String password) {
		 if(username==null||username.equals(" ")) {
			 return ServerResponse.CreateResponseByFail(Const.USERNAME_NOT_NULL, "用户名不能为空");
		 }
		 if(password==null||password.equals(" ")) {
			 return ServerResponse.CreateResponseByFail(Const.PASSWORD_NOT_NULL, "密码不能为空");
		 }
		 boolean isexistusername = isusernameexists(username,password);
		 if(isexistusername) {
			Admin admin=admins.get(username);
			if(password.equals(admin.getPassword())) {
				  return ServerResponse.createServerResponseBySucc();
			  }else{
			  	  return ServerResponse.CreateResponseByFail(Const.PASSWORD_ERROR,"密码不匹配");
			  }
			/* for(int j=0;j<admins.size();j++) {
				 Admin admin = admins.get(j);
				 System.out.println(admin.getPassword());
			  if(password.equals(admin.getPassword())) {
				  return ServerResponse.createServerResponseBySucc();
			  }else{
			  	return ServerResponse.CreateResponseByFail(Const.PASSWORD_ERROR,"密码不匹配");
			  }
			   
			 }*/
		 }else {
			 return ServerResponse.CreateResponseByFail(Const.USERNAME_NOT_EXISTS, "用户名不存在");
		 }
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ServerResponse register(String username, String password) {
			if(username!=null&&password!=null) {
					 Admin admin2=new Admin(username,password);
					 admins.put(username, admin2);
					 return ServerResponse.createServerResponseBySucc("注册成功", admin2);
			}else {
				return ServerResponse.CreateResponseByFail(Const.REGISTER_ERROR, "注册失败");
			}
	}
	@Override
	public boolean isusernameexists(String username, String password) {
		/*for(int i=0;i<admins.size();i++) {
			Admin admin = admins.get(i);
			if(admin==null) {
				continue;
			}
			if(username.equals(admin.getUsername())) {
				return true;
			}
		}
		return false;*/
		return admins.containsKey(username);
	}
    
}
