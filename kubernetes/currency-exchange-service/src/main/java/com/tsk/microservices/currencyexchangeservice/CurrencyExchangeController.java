package com.tsk.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		/*
		 * CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to,
		 * BigDecimal.valueOf(50));
		 */
		logger.info("retriveExchangeValue from {} to {}", from,to);
		CurrencyExchange currencyExchange 
		= repository.findByFromAndTo(from, to);

		if(currencyExchange ==null) {
			throw new RuntimeException
			("Unable to Find data for " + from + " to " + to);
		}

		String port = environment.getProperty("local.server.port");
		//CHANGE-KUBERNETES
				String host = environment.getProperty("HOSTNAME");
				//String version = "v11";
				String version = "v12";

				
				currencyExchange.setEnvironment(port + " " + version + " " + host);
		//currencyExchange.setEnvironment(port);

		return currencyExchange;

	}

}