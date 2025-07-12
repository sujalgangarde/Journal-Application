package com.sujalgangarde.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager dbBean(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

}

// PlatformTransactionManager is used to manage transactions in a Spring application. It has methods to begin, commit, and rollback transactions. In the context of MongoDB, it provides a way to manage transactions across multiple operations on MongoDB collections.
// MongoTransactionManager is a specific implementation of PlatformTransactionManager for MongoDB.