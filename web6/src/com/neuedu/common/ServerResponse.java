package com.neuedu.common;

import com.google.gson.Gson;

//响应前端的高可用对象
public class ServerResponse<T> {
	private int status;
	private String msg;
	private T data;
private ServerResponse() {}
private ServerResponse(int status) {
	this.status=status;
}
private ServerResponse(int status,String msg) {
	this.status=status;
	this.msg=msg;
}
private ServerResponse(int status,T data) {
	this.status=status;
	this.data=data;
}
private ServerResponse(int status,String msg,T data) {
	this.status=status;
	this.msg=msg;
	this.data=data;
}
//判断接口调用是否成功
public boolean isSuccess() {
	return this.status==0;
}
//接口调用成功 status==0
public static ServerResponse createServerResponseBySucc() {
	return new ServerResponse(0);
}
public static ServerResponse createServerResponseBySucc(String msg) {
	return new ServerResponse(0,msg);
}
public static <T> ServerResponse<T> createServerResponseBySucc(String msg,T data) {
	return new ServerResponse(0,msg,data);
}
//接口调用失败 status!=0
public static ServerResponse CreateResponseByFail(int status) {
	return new ServerResponse(status);
}
public static ServerResponse CreateResponseByFail(int status,String msg) {
	return new ServerResponse(status,msg);
}
public String obj2str() {
	Gson gson=new Gson();
	String responText = gson.toJson(this);
	return responText;
}
}
