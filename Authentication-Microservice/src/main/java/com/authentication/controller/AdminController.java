package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.dto.AdForgotPasswordRequest;
import com.authentication.dto.AdLoginRequest;
import com.authentication.entity.Admin;
import com.authentication.entity.Announcement;
import com.authentication.entity.Resident;
import com.authentication.model.AdminManager;
import com.authentication.model.AnnouncementManager;
import com.authentication.model.ResidentManager;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	ResidentManager rm;
	@Autowired
	AdminManager am;
	
	//Adding a Resident (Done by Admin)
	
	@PostMapping("/addresident")
	public String registration(@RequestBody Resident rd)
	{
		return rm.register(rd);
	}
	
	//Admin Login
	
	@PostMapping("/login")
	public String login(@RequestBody AdLoginRequest alr)
	{
		long empid = alr.getEmpid();
		String password = alr.getPassword();
		return am.login(empid, password);
	}
	
	
	
	
	
	
	
  
  
	//Admin Registration
	
	@PostMapping("/addadmin")
	public String registration(@RequestBody Admin ad)
	{
		return am.register(ad);
	}
	
	//Admin Password Change
	
	@PostMapping("/resetpassword")
	public String passwordChange(@RequestBody AdForgotPasswordRequest afpr)
	{
		long empid = afpr.getEmpid();
		String password = afpr.getPassword();
		String newpassword = afpr.getNewpassword();
		return am.forgotPassword(empid, password, newpassword);
	}
	
	//Admin Deletion
	
	@DeleteMapping("/deleteadmin/{empid}")
	public String adDeletion(@PathVariable("empid")long empid)
	{
		return am.deleteAdmin(empid);
	}
	
	
	@GetMapping("/delete")
    public String adDeletion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("empid") != null) {
            long empid = (long) session.getAttribute("empid");
            return "redirect:/deleteadmin/" + empid;
        } else {
            return "User not logged in";
        }
    }
	
	
	
	//Resident Deletion
	
	@DeleteMapping("/deleteresident/{flatno}")
	public String rdDeletion(@PathVariable("flatno")long flatno)
	{
		return rm.deleteResident(flatno);
	}
	
	
	
}
