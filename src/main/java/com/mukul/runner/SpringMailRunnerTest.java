package com.mukul.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.mukul.service.ISpringMailService;
@Component
public class SpringMailRunnerTest implements CommandLineRunner{
	@Autowired
	private ConfigurableApplicationContext run;
	@Autowired
	private ISpringMailService service;

	@Override
	public void run(String... args) throws Exception {
				try {
					
					String msg1 = service.purchaseOrder(new String[] {"shirt", "belt", "pen", "book"},
					       new Double[] {5000.0d,5000.5d,6000.0d,4000.0d},
					       new String[] {"example1@gmail.com", "example2@gmail.com", "example3@gmail.com", "example4@gmail.com"});
			System.out.println(msg1);
		} catch (Exception e) {
					e.printStackTrace();	
		}
		run.close();
	}
}
