package com.worldline.fpl.recruitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.entity.Account;

/**
 * Account repository
 * 
 * @author A525125
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	
}
