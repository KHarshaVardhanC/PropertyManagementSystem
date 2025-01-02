package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.dto.ForgotPasswordRequest;
import com.authentication.dto.LoginRequest;
import com.authentication.model.ResidentManager;

@RestController
@RequestMapping("/resident")
public class ResidentController 
{
	@Autowired
	ResidentManager rm;
	
	//Resident Login
	
	@PostMapping("/login")
	public String loginValidation(@RequestBody LoginRequest lr)
	{
		long flatno = lr.getFlatno();
		String password = lr.getPassword();
		return rm.login(flatno, password);
	}
	

	
	
	
	//Resident Password Change
	@PostMapping("/resetpassword")
	public String passwordChange(@RequestBody ForgotPasswordRequest fpr)
	{
		long flatno = fpr.getFlatno();
		String password = fpr.getPassword();
		String newpassword = fpr.getNewpassword();
		return rm.forgotPassword(flatno, password, newpassword);
	}
	
}
