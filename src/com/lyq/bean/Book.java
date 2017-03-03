package com.lyq.bean;
public class Book {//bName,writer,press,presstime,price,pageNum,sort,barCode,LendNum,state
	
	private String barCode;//图书属性变量： 条形码，作者，书名，出版社，出版时间，价格，借阅状态， 
	private String writer; //图书类别,页数，借阅次数
	private String bName;
	private String press; 
	private String presstime; 
	private double price;	
	private String state;
	private String sort;
	private int pageNum;
	private int LendNum;
	private String borrower;
	private int booknum;
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getstate() {
		return state;
	}
	public void setstate(String sta) {
		state=sta;
	}
	
	public String getsort() {
		return sort;
	}
	public void setsort(String sor) {
		sort=sor;
	}
	
	public String getbName() {
		return bName;
	}
	public void setbName(String name) {
		bName=name;
	}
	
	public String getwriter() {
		return writer;
	}
	public void setwriter(String wri) {
		writer=wri;
	}
	
	public String getpress() {
		return press;
	}
	public void setpress(String pre) {
		press=pre;
	}
	
	public String getpresstime() {
		return presstime;
	}
	public void setpresstime(String pretm) {
		presstime=pretm;
	}
	
	public double getprice() {
		return price;
	}
	public void setprice(double pri) {
		price=pri;
	}
	
	public int getpageNum() {
		return pageNum;
	}
	public void setpageNum(int pnu) {
		pageNum=pnu;
	}
	
	public int getLendNum() {
		return LendNum;
	}
	public void setLendNum(int lnu) {
		LendNum=lnu;
	}
	
	public String getbarCode() {
		return barCode;
	}
	public void setbarCode(String bar) {
		barCode=bar;
	}
}
