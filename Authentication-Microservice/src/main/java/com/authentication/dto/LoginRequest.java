package com.authentication.dto;

public class LoginRequest
{
	private long flatno;
	private String password;
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
	
}
