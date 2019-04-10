package com.neuedu.common;

public enum StudentOperationEnum {
	STUDENTADD(1,"添加学生"),
	STUDENTCHECK(2,"查询全部学生信息"),
	STUDENTSELECTBYID(3,"查询某个学生信息"),
	STUDENTUPDATE(4,"更新学生信息"),
	STUDENTDELETE(5,"删除学生")
	;
	private int operation_type;
	private String operation_desc;
	StudentOperationEnum(int operation_type,String operation_desc){
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
