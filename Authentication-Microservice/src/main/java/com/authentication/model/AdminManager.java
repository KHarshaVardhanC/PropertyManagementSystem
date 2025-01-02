 package com.authentication.model;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.authentication.entity.Admin;
import com.authentication.entity.Resident;
import com.authentication.repository.AdminRepository;

@Service
public class AdminManager
{
	@Autowired
	AdminRepository ar;
	@Autowired
	JavaMailSender jms;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	public String login(Long empid,String password)
	{
		Admin ad = ar.findById(empid).get();
		if(password.equals(ad.getPassword()))
		{
			Calendar calendar = Calendar.getInstance();
		    Date datetime = calendar.getTime();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        String formattedDate = dateFormat.format(datetime);
		    ad.setLastlogin(formattedDate);
		    ar.save(ad);
			return "redirect:/adminHome.html";
		}
		return "Login Failed";
	}
	
	public static int generateFourDigitRandomNumber() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
	
	public static String generatePassword(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
	
	public String register(Admin ad)
	{
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(formatter);
        ad.setDateofjoining(dateString);
		ad.setEmpid(generateFourDigitRandomNumber());
		ad.setPassword(generatePassword(6));
		ar.save(ad);
		try
		{
			String frommail="team.myinfra@gmail.com";
			String subject="Admin Credentials of MyInfra";
			String message="Dear " + ad.getName() + ",\n\n" +
	                "Welcome to the Team MyInfra! Your are registered successfully as an Admin with EmpID" +ad.getEmpid()+".\n"+
	                "You can access the Admin portal with the below credentials.\n" +
	                "Username(Emp ID):"+ad.getEmpid()+".\n"+
	                "Password:"+ad.getPassword()+".\n\n\n"+
	                "With Regards,\n" +
	                "Team MyInfra";
			SimpleMailMessage smm=new SimpleMailMessage();
			smm.setFrom(frommail);
			smm.setTo(ad.getEmail());
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
	public String forgotPassword(long empid,String password,String newpassword)
	{
		Calendar calendar = Calendar.getInstance();
	    Date currentDateTime = calendar.getTime();
		Admin ad = ar.findById(empid).get();
		if(password.equals(ad.getPassword()))
		{
			ad.setPassword(newpassword);
			ar.save(ad);
			try
			{
				String frommail="team.myinfra@gmail.com";
				String subject="!!!!PASSWORD CHANGED!!!!";
				String message="Dear " + ad.getName() + ",\n\n" +
		                "Your account password for EmpID " + ad.getEmpid() + " is changed .\n" +
		                "On" +currentDateTime+". If it is not done by you contact your Authority.\n\n\n"+
		                "With Regards,\n" +
		                "Team MyInfra";
				SimpleMailMessage smm=new SimpleMailMessage();
				smm.setFrom(frommail);
				smm.setTo(ad.getEmail());
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
	public String deleteAdmin(long empid)
	{
	    ar.deleteById(empid);
		return "Deleted Admin Successfully";
	}
}
