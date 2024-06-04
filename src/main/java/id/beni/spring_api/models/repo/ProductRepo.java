package id.beni.spring_api.models.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import id.beni.spring_api.models.entities.Product;
import id.beni.spring_api.models.entities.Supplier;
import jakarta.websocket.server.PathParam;


// JPA QUERY Manual

public interface ProductRepo extends CrudRepository<Product, Long>{
    
    @Query("SELECT p from Product p WHERE p.name= :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p from Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p from Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId")Long categoryId);

    @Query("SELECT p from Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier")Supplier supplier);
}
