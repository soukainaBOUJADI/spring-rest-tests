package com.worldline.fpl.recruitment.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.StartBoot;
import com.worldline.fpl.recruitment.controller.TransactionController;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

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
	
	private static final Logger log = LoggerFactory.getLogger(StartBoot.class);

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
	public ResponseEntity<Transaction> removeTransaction(@PathVariable("id") String id, @PathVariable("accountId") String accountId) {
		String result = this.transactionService.removeTransaction(id, accountId);
		
		if("NO_CONTENT".equals(result)){
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}else if("NOT_FOUND".equals(result)){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else if("OK".equals(result)){
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	public Page<Transaction> addTransaction(@RequestBody Transaction t){
		return this.transactionService.addTranaction(t);
		
	}

}
