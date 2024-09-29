package com.yogayataverma.datastore.controller;

import com.yogayataverma.datastore.model.Model;
import com.yogayataverma.datastore.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/products")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping()
    public List<Model> getAllProducts() {
        System.out.println("Retrieved Products" + service.findAll());
        return service.findAll();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Test endpoint is working");
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getProductImage(@PathVariable String id) {
        Model product = service.findById(id);
        if (product != null && product.getProductImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // This could be adjusted based on image data
            return new ResponseEntity<>(product.getProductImage(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Model> uploadProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") String price, // Changed from double to String
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("storeName") String storeName,
            @RequestParam("productColor") String productColor) throws IOException {
        Model product = new Model();
        product.setProductName(productName);
        product.setPrice(price); // No need for conversion now
        String encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
        product.setProductImage(encodedImage);
        product.setStoreName(storeName);
        product.setProductColor(productColor);
        Model savedProduct = service.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}
