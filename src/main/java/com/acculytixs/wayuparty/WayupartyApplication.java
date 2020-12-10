package com.acculytixs.wayuparty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WayupartyApplication extends SpringBootServletInitializer{
	
	private static final Logger logger = LogManager.getLogger(WayupartyApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(WayupartyApplication.class, args);
		
		logger.info("Info level log message");
		logger.debug("Debug level log message");
		logger.error("Error level log message");
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WayupartyApplication.class);
    }

}
