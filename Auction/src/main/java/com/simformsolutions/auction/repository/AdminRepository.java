package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simformsolutions.auction.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	boolean existsByemail(String email);

}
