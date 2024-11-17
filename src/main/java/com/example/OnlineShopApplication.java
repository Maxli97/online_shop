package com.example;

import com.example.online_shop.model.CustomersRepository;
import com.example.online_shop.model.OrdersRepository;
import com.example.online_shop.model.Product;
import com.example.online_shop.model.ProductRepository;
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
		productRepository.saveAll(products);
	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository,CustomersRepository customersRepository, OrdersRepository ordersRepository) {
		return args -> {
			loadData(productRepository, customersRepository, ordersRepository);
		};
	}

}
