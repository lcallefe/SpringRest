package com.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algafood.infrastructure.repository.CustomizedJpaRepositoryImpl;

@SpringBootApplication
@ComponentScan({"com.algafood.*"})
@EnableJpaRepositories(repositoryBaseClass = CustomizedJpaRepositoryImpl.class) //substitui a implementação do repositório customizado base
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
