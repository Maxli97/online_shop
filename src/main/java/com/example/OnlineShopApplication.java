package com.example;

import com.example.online_shop.model.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class OnlineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	private void loadData(ProductRepository productRepository, CustomersRepository customersRepository, OrdersRepository ordersRepository) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("RTZ 4900", "GPU","Graphics card",420.69,99));
		products.add(new Product("RTX 4090", "GPU","Graphics card",1000,99));
		productRepository.saveAll(products);
		customersRepository.save(new Customers("aaa",""));
		customersRepository.save(new Customers("bbb","{id:'1',amount:'2'}"));
		ordersRepository.save(new Orders("{id:'2',amount:'1'}","1970-01-01 00:00:00",1000,"finish"));

	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository,CustomersRepository customersRepository, OrdersRepository ordersRepository) {
		return args -> {
			loadData(productRepository, customersRepository, ordersRepository);
		};
	}

}
