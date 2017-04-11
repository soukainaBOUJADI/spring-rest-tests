package com.worldline.fpl.recruitment.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

/**
 * Account test
 * 
 * @author A525125
 *
 */
public class TransactionTest extends AbstractTest {
	

	@Test
	public void getTransactions() throws Exception {
		mockMvc.perform(get("/accounts/1/transactions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(3)))
				.andExpect(jsonPath("$.content[0].number", is("12151885120")))
				.andExpect(jsonPath("$.content[0].balance", is(42.12)));
	}

	@Test
	public void getTransactionsNoContent() throws Exception {
		mockMvc.perform(get("/accounts/2/transactions")).andExpect(
				status().isNoContent());
	}

	@Test
	public void getTransactionsOnUnexistingAccount() throws Exception {
		mockMvc.perform(get("/accounts/3/transactions"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("INVALID_ACCOUNT")));
	}
	
	
	@Test
	public void deleteTransaction() throws Exception {
		mockMvc.perform(delete("/accounts/1/transactions/4")).andExpect(
				status().isNoContent());
	}
	
	@Test
	public void deleteUnexistingTransaction() throws Exception {
		mockMvc.perform(delete("/accounts/1/transactions/5"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("INVALID_TRANSACTION")));
	}
	
	@Test
	public void deleteTransactionFromUnexistingAccount() throws Exception {
		mockMvc.perform(delete("/accounts/3/transactions/1"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("INVALID_ACCOUNT")));
	}
	
	@Test
	public void deleteTransactionWhichNotBelongToTheAccount() throws Exception {
		mockMvc.perform(delete("/accounts/2/transactions/2"))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$.errorCode", is("TRANSACTION_NOT_BELONG_TO_ACCOUNT")));	
	}

}
