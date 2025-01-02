package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.authentication.entity.Announcement;
import com.authentication.model.AnnouncementManager;

@RestController
public class AnnouncementController 
{
  @Autowired
  AnnouncementManager am;
  
  @RequestMapping(value="/admin/postannouncement",method = RequestMethod.POST)
  public String newAnnouncement(@RequestBody Announcement ac)
  {
    return am.addAnnouncement(ac);
  }
  
  @RequestMapping(value="/admin/deleteannouncement/{aid}",method = RequestMethod.DELETE)
  public String deleteAnnouncement(@PathVariable("aid")long aid)
  {
    return am.removeAnnouncement(aid);
  }
  
  
  
  @RequestMapping(value="/resident/announcements/Paging/{cpage}/{psize}",method = RequestMethod.GET)
  public String getAnnouncementsPaging(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
  {
    return am.getPaging(cpage, psize).toString();
  }
  
  @RequestMapping(value="/resident/announcements/Data/{cpage}/{psize}",method = RequestMethod.GET)
  public String getAnnouncementsData(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
  {
    return am.getData(cpage, psize).toString();
  }
  
 

}