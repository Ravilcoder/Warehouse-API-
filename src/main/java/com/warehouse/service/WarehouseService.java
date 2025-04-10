package com.warehouse.service;

import com.warehouse.model.Product;
import com.warehouse.model.Warehouse;
import com.warehouse.repository.ProductRepository;
import com.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow();
        warehouse.setName(updatedWarehouse.getName());
        warehouse.setLocation(updatedWarehouse.getLocation());
        warehouse.setCapacity(updatedWarehouse.getCapacity());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> getWarehousesByProductName(String productName) {
        return productRepository.findAll().stream()
                .filter(p -> p.getName().equals(productName))
                .map(Product::getWarehouse)
                .distinct()
                .toList();
    }

    public Map<String, Object> getWarehouseStats(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();
        List<Product> products = productRepository.findAll().stream()
                .filter(p -> p.getWarehouse().getId().equals(warehouseId))
                .toList();

        double occupiedVolume = products.stream()
                .mapToDouble(p -> p.getVolume() * p.getQuantity())
                .sum();
        int uniqueProducts = products.size();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCapacity", warehouse.getCapacity());
        stats.put("occupiedVolume", occupiedVolume);
        stats.put("uniqueProducts", uniqueProducts);
        return stats;
    }
}