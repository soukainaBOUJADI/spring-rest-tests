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
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.ExceptionHandlerService;
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
	public TransactionControllerImpl(TransactionService transactionService ,ExceptionHandlerService exceptionService ) {
		this.transactionService = transactionService;
	}

	@Override
	public ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@PathVariable("accountId") Long accountId,
			@PageableDefault Pageable p) {
		Page<TransactionResponse> page = transactionService
				.getTransactionsByAccount(accountId, p);
		if (null == page || page.getTotalElements() == 0) {
			log.debug("Cannot find transaction for account {}", accountId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok().body(page);
	}

	/* *************************************************************************************************************************************** */
	@Override
	public ResponseEntity<Void> deleteTransactionsByAccount(
			@PathVariable("accountId") 	Long accountId,
			@PathVariable("transactionId")  Long transactionId) {

		this.transactionService.deleteTransactionByAccount(accountId, transactionId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@Override
	public ResponseEntity<TransactionResponse> saveTransaction(
			@PathVariable("accountId") Long accountId,
			@RequestBody Transaction transaction)  {

		TransactionResponse  createdTransaction;

		try{
			createdTransaction = this.transactionService.save(accountId, transaction);

		}catch(ServiceException ex){

			throw new ServiceException(ex.getErrorCode(),  ex.getMessage());
		}	


		return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
	}

	@Override
	public ResponseEntity<TransactionResponse> updateTransaction(
			@PathVariable Long accountId,@PathVariable Long transactionId, 
			@RequestBody Transaction transaction) {
		
			TransactionResponse updatedTransaction ;

			try{
				updatedTransaction = this.transactionService.update(accountId, transactionId, transaction);
		
			}catch(ServiceException ex){

				throw new ServiceException(ex.getErrorCode(),  ex.getMessage());
			}	
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedTransaction);
		}

		

	/* *************************************************************************************************************************************** */

}
