package com.actionForm;

public class AccountForm {
	private int id = -1;
	private String name = "";
	private String pwd = "";
	private String rand = "";
	private String accountType = "";
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd(){
		return pwd;
	}
	
	public String getRand(){
		return rand;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setRand(String rand){
		this.rand = rand;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
