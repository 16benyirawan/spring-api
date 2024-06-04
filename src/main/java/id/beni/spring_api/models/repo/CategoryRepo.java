package id.beni.spring_api.models.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import id.beni.spring_api.models.entities.Category;

public interface  CategoryRepo extends  CrudRepository<Category, Long>{
    
    Page<Category> findByNameContains(String name, Pageable pageable);
}
