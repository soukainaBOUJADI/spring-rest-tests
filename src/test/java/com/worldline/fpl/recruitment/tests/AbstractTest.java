package com.worldline.fpl.recruitment.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.worldline.fpl.recruitment.StartBoot;

/**
 * Abstract test class
 * 
 * @author A525125
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartBoot.class)
@ActiveProfiles("tests")
public abstract class AbstractTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
}
