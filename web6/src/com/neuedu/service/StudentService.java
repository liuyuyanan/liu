package com.neuedu.service;

import java.util.List;

import com.neuedu.common.ServerResponse;
import com.nueedu.pojo.Admin;
import com.nueedu.pojo.Student;

public interface StudentService {
	public ServerResponse<Student> add(int id, String name, String sex, Integer age, String _class, String address, String phone,
			String email,Integer score);
	public ServerResponse<Student> check();
	public ServerResponse<Student> selectById(Integer id);
	public ServerResponse<Student> update(Integer id);
	public ServerResponse<Student> delete(Integer id);
}
