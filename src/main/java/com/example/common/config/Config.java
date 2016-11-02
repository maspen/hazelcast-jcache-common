package com.example.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.common.cache.SimpleProductCacheKeyGenerator;
import com.example.common.repository.IProductRepository;
import com.example.common.repository.ProductRepositoryImpl;

// need this for testing
@Configuration
@ComponentScan(basePackages = {"com.example.common"})
public class Config {

	@Bean
	public IProductRepository productRepository() {
		return new ProductRepositoryImpl();
	}
	
	// needed?
	@Bean
	public SimpleProductCacheKeyGenerator keyGenerator() {
		return new SimpleProductCacheKeyGenerator();
	}
}
