package com.warehouse.repository;

import java.util.List;
import com.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByWarehouseId(Long warehouseId);
}
