package com.lyq.bean;

public class Reader {
	private String rNo;  //用户属性：学号/工号，用户名，性别，年龄，班级/部门，用户类别，
	private String rName; //电话号码,借阅数量，可以借阅数量
	private String rsex;
	private int rage; 
	private String rClass;
	private String rSort; 
	private String rPhoneNum;
	private int lendNum;
	private int canLendNum;
	
	public int getCanLendNum() {
		return canLendNum;
	}
	public void setCanLendNum(int canLendNum) {
		this.canLendNum = canLendNum;
	}
	public int getLendNum() {
		return lendNum;
	}
	public void setLendNum(int lendNum) {
		this.lendNum = lendNum;
	}
	public String getrNo() {
		return rNo;
	}
	public void setrNo(String rno) {
		rNo=rno;
	}
	
	public String getrName() {
		return rName;
	}
	public void setrName(String rna) {
		rName=rna;
	}
	
	public String getrsex() {
		return rsex;
	}
	public void setrsex(String rse) {
		rsex=rse;
	}
	
	public int getrage() {
		return rage;
	}
	public void setrage(int rag) {
		rage=rag;
	}
	
	public String getrClass() {
		return rClass;
	}
	public void setrClass(String rcl) {
		rClass=rcl;
	}
	
	public String getrSort() {
		return rSort;
	}
	public void setrSort(String rso) {
		rSort=rso;
	}
	
	public String getrPhoneNum() {
		return rPhoneNum;
	}
	public void setrPhoneNum(String rpn) {
		rPhoneNum=rpn;
	}

}
