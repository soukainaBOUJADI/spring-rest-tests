package com.worldline.fpl.recruitment.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.ErrorResponse;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

	/**
	 * Get transaction list by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return the transaction list
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get transaction list related to an account", response = TransactionResponse.class, responseContainer = "List")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 204, message = "No transactions", response = ErrorResponse.class) })
	ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Pageable information") @PageableDefault Pageable p);
	
	/**
	 * Removes the transactions by account.
	 *
	 * @param accountId the account id
	 * @param transactionId the transaction id
	 * @return the response entity
	 */
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "remove transaction related to an account", response = Void.class)
	@ApiResponses({
		    @ApiResponse(code = 404, message = "Transaction not Found Or Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Transaction not belong to the account", response = ErrorResponse.class),
			})
	ResponseEntity<Void> deleteTransactionsByAccount(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Transaction ID") @PathVariable("transactionId") String transactionId);
	

	
	/**
	 * Add the transaction to account.
	 *
	 * @param accountId the account id
	 * @param transaction the transaction
	 * @return the response entity
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "save transaction related to an account", response = TransactionResponse.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "Invalid Transaction", response = ErrorResponse.class)})
	ResponseEntity<TransactionResponse> addTransactionToAccount(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Transaction") @RequestBody Transaction transaction);
	
	

	/**
	 * Update transaction.
	 *
	 * @param accountId the account id
	 * @param transactionId the transaction id
	 * @param transaction the transaction
	 * @return the response entity
	 */
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT)
	@ApiOperation(value = "update transaction related to an account", response = Void.class)
	@ApiResponses({
		    @ApiResponse(code = 404, message = "Transaction not Found Or Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "Invalid Transaction", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Transaction not belong to the account", response = ErrorResponse.class),})
	ResponseEntity<Void> updateTransaction(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Transaction ID") @PathVariable("transactionId") String transactionId,
			@ApiParam("Transaction") @RequestBody Transaction transaction);
	
	
}
