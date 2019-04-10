package com.neuedu.common;

public enum AdminOperationEnum{
    ADMIN_LOGIN(1,"管理员登录"),
	ADMIN_REGISTER(2,"管理员注册")
	;
	private int operation_type;
	private String operation_desc;
	AdminOperationEnum(int operation_type,String operation_desc){
		this.operation_type=operation_type;
		this.operation_desc=operation_desc;
	}
	public int getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(int operation_type) {
		this.operation_type = operation_type;
	}
	public String getOperation_desc() {
		return operation_desc;
	}
	public void setOperation_desc(String operation_desc) {
		this.operation_desc = operation_desc;
	}
   
}