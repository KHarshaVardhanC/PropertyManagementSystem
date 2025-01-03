package com.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.entity.Resident;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long>
{
	
}
