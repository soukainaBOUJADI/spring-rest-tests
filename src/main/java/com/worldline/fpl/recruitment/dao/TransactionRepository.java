package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);
	
	/**
	 * Delete transaction by account.
	 *
	 * @param accountId 
	 *            the account id
	 * @param transactionId 
	 *            the transaction id
	 */
	void deleteTransactionByAccount(String accountId, String transactionId);

	
	/**
	 * Check if an account exists
	 *
	 * @param transactionId 
	 *            the transaction id
	 * @return true, 
	 * 			  if successful
	 */
	boolean exists(String transactionId);


	/**
	 * Check if transaction is belong to account
	 *
	 * @param accountId 
	 * 				the account id
	 * @param transactionId 
	 * 				the transaction id
	 * @return true, 
	 * 				if successful
	 */
	boolean transactionBelongToAccount(String accountId, String transactionId);
	
	/**
	 * Save the transaction.
	 *
	 * @param transaction 
	 * 			the transaction
	 * @return
	 * 		    the transaction
	 */
	Transaction save(Transaction transaction);
}
