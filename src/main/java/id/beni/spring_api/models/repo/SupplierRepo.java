package id.beni.spring_api.models.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import id.beni.spring_api.models.entities.Supplier;

// DERIVED QUERY BY Method

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
    Supplier findByEmail(String email);

    // sama dengan like
    List<Supplier> findByNameContains(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
