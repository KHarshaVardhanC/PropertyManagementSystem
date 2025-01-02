package com.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.authentication.entity.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>
{
  @Query("select count(A) from Announcement A")
  public int totRecords();
}