package id.beni.spring_api.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.beni.spring_api.dto.ResponseData;
import id.beni.spring_api.dto.SearchData;
import id.beni.spring_api.dto.SupplierData;
import id.beni.spring_api.models.entities.Supplier;
import id.beni.spring_api.services.SupplierService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping("/save")
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/find/{id}")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }


    @PutMapping("/edit")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/byemail")
    public Supplier findByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/byname")
    public List<Supplier> findByNameContains(@RequestBody SearchData searchData){
        return supplierService.findByNameContains(searchData.getSearchKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Supplier> findByNameStartingWith(@RequestBody SearchData searchData){
        return supplierService.findByNameStartingWith(searchData.getSearchKey());
    }

    @PostMapping("/search/nameORemail")
    public List<Supplier> findByNameContainsOrEmailContains(@RequestBody SearchData searchData){
        return supplierService.findByNameContainsOrEmailContains(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }

}
