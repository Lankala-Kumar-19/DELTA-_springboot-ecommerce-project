package com.delta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DeltaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeltaApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
//	    return args -> {
//	        
//	            User user = new User();
//	            user.setName("mad");
//	            user.setEmail("dogm6787example.com");
//	            user.setPassword(encoder.encode("admin123"));
//	            user.setRole(Role.ADMIN);
//	            repo.save(user);
//	        
//	    };
//	}

}
