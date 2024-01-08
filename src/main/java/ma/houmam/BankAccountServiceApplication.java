package ma.houmam;

import ma.houmam.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		CommandLineRunner commandLineRunner = args -> {
			accountService.populateData();
		};
		return commandLineRunner;
	}
}
