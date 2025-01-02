package com.authentication.dto;

public class ForgotPasswordRequest {
	private long flatno;
	private String password;
	private String newpassword;
	public long getFlatno() {
		return flatno;
	}
	public void setFlatno(long flatno) {
		this.flatno = flatno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
}
