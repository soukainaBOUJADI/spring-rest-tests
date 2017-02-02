package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * Transaction entity
 * 
 * @author A525125
 *
 */
@Data
public class Transaction implements Serializable {

	private static final long serialVersionUID = 706690724306325415L;

	private String id;

	private String accountId;

	private String number;

	private BigDecimal balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
