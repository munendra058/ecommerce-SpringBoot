package com.spring.ecommerce.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class productController {

    @Autowired
    private ProductService service;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProdcuts(HttpServletRequest session) {
        System.out.println("print session id" + session.getSession().getId());
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") int id) {

        Product pdt = service.findByProduct(id);

        if (pdt != null)
            return new ResponseEntity<>(pdt, HttpStatus.OK);
        else

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        // byte[] image_data = service.getProductImage(id);

        // HttpHeaders http = new HttpHeaders();
        // http.setContentType(MediaType.IMAGE_JPEG);

        Product data = service.findByProduct(id);
        if (data != null)
            return new ResponseEntity<>(data.getImageData(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addproduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("imageFile") MultipartFile imageFile) {
        Product saveProduct = null;
        try {
            // Deserialize JSON string to Product object
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);
            saveProduct = service.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
            @RequestPart MultipartFile imageFile) {

        Product updaProduct = null;

        try {
            updaProduct = service.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        Product checkProduct = service.findByProduct(id);
        if (checkProduct != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searcProduct(@RequestParam String keyword) {
        List<Product> searchList = service.searcProduct(keyword);
        System.out.println("Print Keyword" + keyword);
        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }

}
