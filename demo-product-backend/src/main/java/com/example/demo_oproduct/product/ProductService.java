package com.example.demo_oproduct.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(@Valid Product product){
        product.setId(UUID.randomUUID());
        product = productRepository.save(product);
        return product;
    }

    public Product getProductById(String id){
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        return product.get();
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product updateProduct(String id, Product product) {
        product.setId(UUID.fromString(id));
        product = productRepository.save(product);
        return product;
    }

    public void deleteProduct(String id){
        productRepository.deleteById(UUID.fromString(id));
    }

    public List<Product> getProductsByCategory(String category){
        List<Product> products = productRepository.findByCategory(category);
        return products;
    }

}
