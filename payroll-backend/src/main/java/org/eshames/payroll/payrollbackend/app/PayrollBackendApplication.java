package org.eshames.payroll.payrollbackend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.eshames.payroll.payrollbackend")
public class PayrollBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollBackendApplication.class, args);
	}

}
