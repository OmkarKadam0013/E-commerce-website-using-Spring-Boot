package com.omkar.MainPackage.Controller;

import com.omkar.MainPackage.Model.Product;
import com.omkar.MainPackage.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService obj;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(obj.getProducts(), HttpStatus.OK);

    }
    @GetMapping("/products/{ProdId}")
    public ResponseEntity<Product> getProductById(@PathVariable  int ProdId) {
        Product product = obj.getProductById(ProdId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProduct(
            @RequestPart("product") Product product,   @RequestPart("imageFile") MultipartFile file){
        try {
            if(file != null && !file.isEmpty()) {
                product.setImageName(file.getOriginalFilename());
                product.setImage(file.getBytes());
                product.setImageType(file.getContentType());
            }
        }catch (Exception e){
            System.out.println("Getting an error in file storing "+e.getMessage());
        }
        obj.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Creating the api for the getting an image
    @GetMapping("/products/{ProdId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int ProdId){
        Product product = obj.getProductById(ProdId);
        if(product != null){
            return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(product.getImage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/products/update")
    public ResponseEntity<HttpStatus> UpdateProduct(@RequestBody Product product){
        obj.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/products/{ProdId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int ProdId){
        obj.deleteProduct(ProdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    }