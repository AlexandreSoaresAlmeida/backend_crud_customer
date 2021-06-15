package com.apptek.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apptek.customer.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

}
