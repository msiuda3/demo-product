package com.example.demo_oproduct.product;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {
    @Query("SELECT * FROM product WHERE category = ?0 ALLOW FILTERING")
    List<Product> findByCategory(String category);
}
