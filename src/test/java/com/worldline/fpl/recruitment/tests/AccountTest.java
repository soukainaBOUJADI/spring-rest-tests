package com.worldline.fpl.recruitment.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Account test
 * 
 * @author A525125
 *
 */
public class AccountTest extends AbstractTest {

	@Test
	public void getAccounts() throws Exception {
		mockMvc.perform(get("/accounts")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(2)))
				.andExpect(jsonPath("$.content[0].type", is("SAVING")))
				.andExpect(jsonPath("$.content[0].balance", is(4210.42)));
	}

	@Test
	public void getAccountDetails() throws Exception {
		mockMvc.perform(get("/accounts/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.active", is(true)))
				.andExpect(jsonPath("$.type", is("SAVING")))
				.andExpect(jsonPath("$.balance", is(4210.42)));
	}

	@Test
	public void getAccountDetailsOnUnexistingAccount() throws Exception {
		mockMvc.perform(get("/accounts/33")).andExpect(status().isNotFound())
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
	
	@Test
	public void addTransactionOnUnexistingAccount() throws Exception {
		String request = getRequest("createOk");

		mockMvc.perform(
				post("/accounts/3/transactions/").contentType(
						MediaType.APPLICATION_JSON).content(request))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorCode", is("INVALID_ACCOUNT")));	
	}


	/** 
	 * Get json request from test file
	 * 
	 * @param name
	 *            the filename
	 * @return the request
	 * @throws IOException
	 */
	private String getRequest(String name) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("json/" + name + ".json"), writer);
		return writer.toString();
	}
}
