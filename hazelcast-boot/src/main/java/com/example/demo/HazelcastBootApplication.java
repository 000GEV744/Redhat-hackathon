package com.example.demo;


import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.UserAccount;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class HazelcastBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastBootApplication.class, args);
	}
	
	/*
	 * @Bean public Map<String, UserAccount> accountMap() { return new HashMap<>();
	 * }
	 */
	@Bean
	public Config hazelCastConfig() {
		return new Config().setManagementCenterConfig(
				new ManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8080/hazelcast-mancenter"));

	}

	@Bean
	public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
		return Hazelcast.newHazelcastInstance(hazelCastConfig);
	}

	@Bean
	public Map<String, UserAccount> accountMap(HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getMap("accountMap");
	}

}
