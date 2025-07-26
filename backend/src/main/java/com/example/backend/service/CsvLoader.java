package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import jakarta.annotation.PostConstruct; // use jakarta.* for Spring Boot 3+
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import com.example.backend.model.Product;


@Service
public class CsvLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void load() {
        loadProducts();
    }

    public void loadProducts() {
        try {
            ClassPathResource resource = new ClassPathResource("static/products.csv");
            InputStream input = resource.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(input));
            String[] nextLine;
            List<Product> products = new ArrayList<>();

            // Skip header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Product product = new Product();
                product.setName(nextLine[0]);
                product.setCategory(nextLine[1]);
                product.setPrice(Double.parseDouble(nextLine[2]));
                product.setBrand(nextLine[3]);
                product.setRating(Double.parseDouble(nextLine[4]));

                products.add(product);
            }

            productRepository.saveAll(products);
            System.out.println("✔ Loaded " + products.size() + " products from products.csv");

        } catch (IOException | CsvValidationException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
