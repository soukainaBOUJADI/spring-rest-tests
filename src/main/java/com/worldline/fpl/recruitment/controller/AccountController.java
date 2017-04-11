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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.json.AccountDetailsResponse;
import com.worldline.fpl.recruitment.json.AccountResponse;
import com.worldline.fpl.recruitment.json.ErrorResponse;

/**
 * Account controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AccountController {

	/**
	 * Get account list
	 * 
	 * @param p
	 *            the pageable information
	 * @return the account list
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get account list", response = AccountResponse.class, responseContainer = "List")
	@ApiResponses({ @ApiResponse(code = 204, message = "No accounts", response = ErrorResponse.class) })
	ResponseEntity<Page<AccountResponse>> getAccounts(
			@ApiParam("Pageable information") @PageableDefault Pageable p);

	/**
	 * Get account details
	 * 
	 * @param accountId
	 *            the account id
	 * @return the account details
	 */
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get account details", response = AccountDetailsResponse.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class) })
	ResponseEntity<AccountDetailsResponse> getAccountDetails(
			@ApiParam("Account") @PathVariable("accountId") Long accountId);

}
