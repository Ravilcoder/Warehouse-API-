package com.warehouse.repository;

import com.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
