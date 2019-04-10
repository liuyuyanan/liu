package com.neuedu.common;

public enum StudentOperationEnum {
	STUDENTADD(1,"���ѧ��"),
	STUDENTCHECK(2,"��ѯȫ��ѧ����Ϣ"),
	STUDENTSELECTBYID(3,"��ѯĳ��ѧ����Ϣ"),
	STUDENTUPDATE(4,"����ѧ����Ϣ"),
	STUDENTDELETE(5,"ɾ��ѧ��")
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
