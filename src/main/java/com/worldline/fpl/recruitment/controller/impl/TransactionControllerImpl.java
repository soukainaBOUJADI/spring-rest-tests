package com.worldline.fpl.recruitment.controller.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.TransactionController;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.TransactionService;

/**
 * Implementation of {@link TransactionController}
 * 
 * @author A525125
 *
 */
@Slf4j
@RestController
public class TransactionControllerImpl implements TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionControllerImpl(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@Override
	public ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@PathVariable("accountId") String accountId,
			@PageableDefault Pageable p) {
		Page<TransactionResponse> page = transactionService
				.getTransactionsByAccount(accountId, p);
		if (null == page || page.getTotalElements() == 0) {
			log.debug("Cannot find transaction for account {}", accountId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok().body(page);
	}
	
	@Override
	public ResponseEntity<Void> deleteTransactionsByAccount(
			@PathVariable("accountId") 	String accountId,
			@PathVariable("transactionId")  String transactionId) {
	
		this.transactionService.deleteTransactionByAccount(accountId, transactionId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@Override
	public ResponseEntity<TransactionResponse> addTransactionToAccount(
			@PathVariable("accountId") String accountId,
			@RequestBody Transaction transaction)  {

		TransactionResponse createdTransaction = this.transactionService.addTransactionToAccount(accountId, transaction);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
	}
	
	@Override
	public ResponseEntity<Void> updateTransaction(
			@PathVariable String accountId,@PathVariable String transactionId, 
			@RequestBody Transaction transaction) {
		
			this.transactionService.update(accountId, transactionId, transaction);
		
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

}
