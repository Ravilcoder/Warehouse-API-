package com.warehouse.service;

import java.util.List;
import com.warehouse.model.Product;
import com.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updatedProduct.getName());
        product.setQuantity(updatedProduct.getQuantity());
        product.setVolume(updatedProduct.getVolume());
        product.setWarehouse(updatedProduct.getWarehouse());
        return productRepository.save(product);
    }

    public void moveProduct(Long productId, Long newWarehouseId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.getWarehouse().setId(newWarehouseId); // Перемещение
        productRepository.save(product);
    }
}
