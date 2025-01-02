package com.complaints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.complaints.entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>
{
	@Query("select count(C) from Complaint C")
	public int totRecords();
}
