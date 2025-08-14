package com.omkar.MainPackage.Repository;

import com.omkar.MainPackage.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Product, Integer> {
}
