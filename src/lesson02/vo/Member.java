package lesson02.vo;

import java.util.Date;

public class Member {
	private int mno;
	private String email;
	private String password;
	private String mname;
	private Date creDate;
	private Date modDate;
	public int getMno() {
		return mno;
	}
	public Member setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getMname() {
		return mname;
	}
	public Member setMname(String mname) {
		this.mname = mname;
		return this;
	}
	public Date getCreDate() {
		return creDate;
	}
	public Member setCreDate(Date creDate) {
		this.creDate = creDate;
		return this;
	}
	public Date getModDate() {
		return modDate;
	}
	public Member setModDate(Date modDate) {
		this.modDate = modDate;
		return this;
	}
	
}
