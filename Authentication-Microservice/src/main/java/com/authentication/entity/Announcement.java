package com.authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="announcements")
public class Announcement
{
  @Id
  long aid;
  String message;
  String date;
  String time;
  public long getAid() {
    return aid;
  }
  public void setAid(long aid) {
    this.aid = aid;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
}