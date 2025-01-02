package com.authentication.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.authentication.entity.Resident;
import com.authentication.repository.ResidentRepository;

@Service
public class ResidentManager 
{
	@Autowired
	ResidentRepository rr;
	@Autowired
	JavaMailSender jms;
	public String login(Long flatno,String password)
	{
		Resident rd = rr.findById(flatno).get();
		if(password.equals(rd.getPassword()))
		{
			return "redirect:/residentHome.html";
		}
		return "Login Failed";
	}
	
	public String register(Resident rd)
	{
		rd.setPassword(rd.getDob());
		rr.save(rd);
		try
		{
			String frommail="team.myinfra@gmail.com";
			String subject="Registration Successful!!!!";
			String message="Dear " + rd.getName() + ",\n\n" +
	                "Welcome to MyInfra! Your registration for flat " + rd.getFlatno() + " is successful.\n" +
	                "You can access the portal with your FlatNumber as Username and the password is your Date of Birth (DD-MM-YYYY).\n\n" +
	                "With Regards,\n" +
	                "Team MyInfra";
			SimpleMailMessage smm=new SimpleMailMessage();
			smm.setFrom(frommail);
			smm.setTo(rd.getEmail());
			smm.setSubject(subject);
			smm.setText(message);
			jms.send(smm);
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return "Registered Successfully.Please Check Your Email for Credentials";
	}
	
	public String forgotPassword(long flatno,String password,String newpassword)
	{
		Calendar calendar = Calendar.getInstance();
	    Date currentDateTime = calendar.getTime();
		Resident rd = rr.findById(flatno).get();
		if(password.equals(rd.getPassword()))
		{
			rd.setPassword(newpassword);
			rr.save(rd);
			try
			{
				String frommail="team.myinfra@gmail.com";
				String subject="!!!!PASSWORD CHANGED!!!!";
				String message="Dear " + rd.getName() + ",\n\n" +
		                "Your account password for flat " + rd.getFlatno() + " is changed .\n" +
		                "On" +currentDateTime+"If it is not done by you contact your Admin .\n.\n"+
		                "With Regards,\n" +
		                "Team MyInfra";
				SimpleMailMessage smm=new SimpleMailMessage();
				smm.setFrom(frommail);
				smm.setTo(rd.getEmail());
				smm.setSubject(subject);
				smm.setText(message);
				jms.send(smm);
			}
			catch(Exception e)
			{
				e.getMessage();
			}
			return "Password Changed Sucessfully";
		}
		return "Invalid Credentials";
	}
	public String deleteResident(long flatno)
	{
		rr.deleteById(flatno);
		return "Deleted Resident data Successfully";
	}
}
