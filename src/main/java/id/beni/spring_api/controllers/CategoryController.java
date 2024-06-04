package id.beni.spring_api.controllers;

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

import id.beni.spring_api.dto.CategoryData;
import id.beni.spring_api.dto.ResponseData;
import id.beni.spring_api.models.entities.Category;
import id.beni.spring_api.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/find/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @PutMapping()
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    // @PostMapping("/search/{size}/{page}")
    // public Iterable<Category> findByNameContains(@RequestBody SearchData searchData, @PathVariable("size") int size, 
    // @PathVariable("page") int page){

    //     Pageable pageable = PageRequest.of(page, size);
    //     return categoryService.findByNameContains(searchData.getSearchKey(), pageable);
    // }

}
