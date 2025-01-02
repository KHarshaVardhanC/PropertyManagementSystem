package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.entity.Complaint;
import com.authentication.model.ComplaintManager;

@RestController
public class ComplaintController
{
	@Autowired
	ComplaintManager cm; 
	
	@RequestMapping(value="/resident/raisecomplaint",method = RequestMethod.POST)
	public String newComplaint(@RequestBody Complaint c)
	{
		return cm.addComplaint(c);
	}
	
	@RequestMapping(value="/admin/allcomplaints/Paging/{cpage}/{psize}",method = RequestMethod.GET)
	public String getComplaintsPaging(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
	{
		return cm.getPaging(cpage, psize).toString();
	}
	
	@RequestMapping(value="/admin/allcomplaints/Data/{cpage}/{psize}",method = RequestMethod.GET)
	public String getComplaintsData(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
	{
		return cm.getData(cpage, psize).toString();
	}

}
