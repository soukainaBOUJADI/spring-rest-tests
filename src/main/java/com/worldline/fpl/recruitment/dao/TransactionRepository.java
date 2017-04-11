package com.worldline.fpl.recruitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
}
