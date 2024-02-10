package com.ranushan;

import com.ranushan.dao.CatDao;
import com.ranushan.domain.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CatMashBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatMashBackEndApplication.class, args);
	}

	@Autowired
	CatDao catDao;

	@Bean
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {

			System.out.println("Running.....");

			Cat b1 = new Cat();
			b1.setId("1");
			b1.setUrl("url1");
			b1.setVotes(1);
			Cat b2 = new Cat();
			b2.setId("2");
			b2.setUrl("url2");
			b2.setVotes(2);

			catDao.saveAll(List.of(b1, b2));
		};
	}

}
