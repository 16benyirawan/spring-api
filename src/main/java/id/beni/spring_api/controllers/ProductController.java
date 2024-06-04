package id.beni.spring_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.beni.spring_api.dto.ResponseData;
import id.beni.spring_api.dto.SearchData;
import id.beni.spring_api.models.entities.Product;
import id.beni.spring_api.models.entities.Supplier;
import id.beni.spring_api.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping("/save")
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/find/{id}")
    public Product findOne(@PathVariable("id")Long id){
        return productService.findOne(id);
    }

    @PutMapping("/edit/")
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public String removeOne(@PathVariable("id")Long id){
        productService.removeOne(id);
        return "Delete Succesfully";
    }

    @PostMapping("/save/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){

        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){

        return productService.findProductByName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData){

        return productService.findProductByNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findProductByCategory(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productService.findProductBySupplier(supplierId);
    }
}
