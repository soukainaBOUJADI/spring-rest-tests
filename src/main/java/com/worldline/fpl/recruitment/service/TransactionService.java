package com.worldline.fpl.recruitment.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction service
 * 
 * @author A525125
 *
 */
@Service
public class TransactionService {
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionRepository transactionRepository;


	public TransactionService(AccountService accountService,
			TransactionRepository transactionRepository) {
		this.accountService = accountService;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable object
	 * @return
	 */
	public Page<TransactionResponse> getTransactionsByAccount(Long accountId,
			Pageable p) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		
		return new PageImpl<TransactionResponse>(accountService.findAccountById(accountId).getTransactions()
				.stream()
				.map(this::map).collect(Collectors.toList()));
		
	}

	
	/* *************************************************************************************************************************************** */
	/**
	 * Delete transaction by account.
	 *
	 * @param accountId the account id
	 * @param transactionId the transaction id
	 */
    @Transactional
	public void deleteTransactionByAccount(Long accountId,Long transactionId) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"Account doesn't exist");
		}
		
		Transaction transaction = Optional.ofNullable(transactionRepository.findOne(transactionId)).orElseThrow(
				() -> new ServiceException(ErrorCode.INVALID_TRANSACTION,
						"Transaction doesn't exist"));

		if (!transaction.getAccount().getId().equals(accountId)) {
			throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_TO_ACCOUNT,
					"transaction not belong to the account");
		}
		
		transactionRepository.delete(transactionId);
	}
	
	
	/**
	 * Save.
	 *
	 * @param accountId the account id
	 * @param transaction the transaction
	 * @return the transaction response
	 */
    @Transactional
	public TransactionResponse save(Long accountId, Transaction transaction){
		
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"The id account "+accountId+" doesn't exist");
		}
		if(transaction.getBalance() == null){
			throw new ServiceException(ErrorCode.INVALID_REQUEST,
					"Balance attribute must not be null");
		}
		if(transaction.getNumber() == null){
			throw new ServiceException(ErrorCode.INVALID_REQUEST,
					"Number attribute must not be null");
		}
		transaction.setAccount(accountService.findAccountById(accountId));
		return map(transactionRepository.save(transaction));
		
	}
	
 
  /**
   * Update.
   *
   * @param accountId the account id
   * @param transactionId the transaction id
   * @param transaction the transaction
   * @return the transaction response
   */
  @Transactional
  public TransactionResponse update(Long accountId,  Long transactionId , Transaction transaction){
		
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT,
					"The id account "+accountId+" doesn't exist");
		}
		Transaction returnedTransaction = Optional.ofNullable(transactionRepository.findOne(transactionId)).orElseThrow(
				() -> new ServiceException(ErrorCode.INVALID_TRANSACTION,
						"Transaction doesn't exist"));

		if (!returnedTransaction.getAccount().getId().equals(accountId)) {
			throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_TO_ACCOUNT,
					"transaction not belong to the account");
		}
		
		if(transaction.getNumber() == null){
			throw new ServiceException(ErrorCode.INVALID_REQUEST,
					"Number attribute must not be null");
		}
		if(transaction.getBalance() == null){
			throw new ServiceException(ErrorCode.INVALID_REQUEST,
					"Balance attribute must not be null");
		}
		
		
		transaction.setAccount(accountService.findAccountById(accountId));
		transaction.setId(transactionId);
		return map(transactionRepository.saveAndFlush(transaction));
		
	}
		
	
	
	/* *************************************************************************************************************************************** */
	
	/**
	 * Map {@link Transaction} to {@link TransactionResponse}
	 * 
	 * @param transaction
	 * @return
	 */
	private TransactionResponse map(Transaction transaction) {
		TransactionResponse result = new TransactionResponse();
		result.setBalance(transaction.getBalance());
		result.setId(transaction.getId());
		result.setNumber(transaction.getNumber());
		return result;
	}

}
