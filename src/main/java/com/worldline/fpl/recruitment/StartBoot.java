package com.worldline.fpl.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Application entry point
 * 
 * @author A525125
 *
 */
@SpringBootApplication
@Slf4j
public class StartBoot {
	
	private static final Logger log = LoggerFactory.getLogger(StartBoot.class); 
	
	public static void main(String[] args) {
		log.info("Start application ...");
		SpringApplication.run(StartBoot.class, args);
	}

}
