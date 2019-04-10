package com.neuedu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.StudentService;
import com.nueedu.pojo.Admin;
import com.nueedu.pojo.Student;

import sun.security.jca.GetInstance;

public class StudentServiceImpl implements StudentService {
	private static StudentServiceImpl instance;
	Map<Integer,Student> students=new HashMap();
	private StudentServiceImpl() {
		Student student1=new Student(1,"С��", "��", 19, "�м�", "����ж�����", "13465438765",
				"324889499@qq.com",89);
		Student student2=new Student(2,"С��", "Ů", 16, "����", "̫ԭ���ӻ���", "13456789078",
				"324856989@qq.com",78);	
		Student student3=new Student(3,"Сѩ", "Ů", 21, "�߼�", "ɽ��ʡ��ͬ��", "16789054378",
				"324982849@qq.com",99);
		students.put(student1.getId(),student1);
		students.put(student2.getId(),student2);
		students.put(student3.getId(),student3);
	}
	public synchronized static StudentServiceImpl GetInstance() {
		if(instance==null) {
			instance=new StudentServiceImpl();
		}
		return new StudentServiceImpl();
	}
	@SuppressWarnings("unchecked")
	@Override
	public  ServerResponse<Student> add(int id, String name, String sex, Integer age, String _class, String address, String phone,
			String email,Integer score) {
		Student student4=new Student( id,  name,  sex, age, _class, address, phone, email,score);
		students.put(student4.getId(), student4);
		return   ServerResponse.createServerResponseBySucc("��ӳɹ�");
	}

	@Override
	public ServerResponse<Student> check() {
		return null;
	}
	@Override
	public ServerResponse<Student> selectById(Integer id) {
		Student student = students.get(id);
		return ServerResponse.createServerResponseBySucc("���ҳɹ�", student);
	}

	@Override
	public ServerResponse<Student> update(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<Student> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
