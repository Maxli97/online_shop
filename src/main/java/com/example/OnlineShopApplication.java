package com.example;

import com.example.model.Product;
import com.example.model.ProductRepository;
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

	private void loadData(ProductRepository productRepository) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("RTZ 4900", "GPU","Graphics card",420.69,99));
		productRepository.saveAll(products);
	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository) {
		return args -> {
			loadData(productRepository);
		};
	}

}
