package com.spring.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {

        return repo.findAll();

    }

    public Product findByProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }

    public void deleteProduct(int id) {

        repo.deleteById(id);
    }

    public List<Product> searcProduct(String keyword) {

        return repo.searchProducts(keyword);
    }

    public byte[] getProductImage(int id) {
        Product product = repo.findById(id).orElse(null);
        return product.getImageData();
    }

}
