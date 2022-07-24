package com.ankhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
