package com.authentication.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.authentication.entity.Announcement;
import com.authentication.repository.AnnouncementRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class AnnouncementManager 
{
  @Autowired
  AnnouncementRepository ar;
  
  public String addAnnouncement(Announcement ac)
  {
    LocalDate currentDate = LocalDate.now();
    String dateString = currentDate.toString();
        LocalTime currentTime = LocalTime.now();
        String timeString = currentTime.toString();
        ac.setDate(dateString);
        ac.setTime(timeString);
    ar.save(ac);
    return "Posted Announcement";
  }
  
  public String removeAnnouncement(long aid)
  {
    ar.deleteById(aid);
    return "Annoucement Deleted Successfully";
  }
  
  public List<String> getData(int cpage, int psize)
  {
    List<String> list = new ArrayList<String>();
    for(Announcement A : ar.findAll(PageRequest.of(cpage - 1, psize)))
    {
      list.add(toJSON(A));
    }
    return list;
  }
  
  //Paging
  public List<String> getPaging(int cpage, int psize)
  {
    int totRecords = ar.totRecords();
    int pageCount = (totRecords / psize) + (totRecords % psize > 0 ? 1 : 0);
    int startPage = 1;
    int endPage = pageCount;
    
    List<String> list = new ArrayList<String>();
    for(int page=startPage; page<=endPage; page++)
    {
      PageData P = new PageData(page, page==cpage);
      list.add(toJSON(P));
    }
    return list;
  }
  
  //Convert JAVA object to JSON
  public String toJSON(Object obj)
  {
    GsonBuilder GB = new GsonBuilder();
    Gson G = GB.create();
    return G.toJson(obj);
  }
  
  
}