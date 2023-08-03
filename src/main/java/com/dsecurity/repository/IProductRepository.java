package com.dsecurity.repository;

import com.dsecurity.model.Catalog;
import com.dsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameContaining(String name);

    List<Product> findByCatalog_CatalogId(Long catalogId);

    @Query(value = "from Catalog c where c.catalogId=:productId")
    Catalog findCatalogByIdProduct(@Param("productId") Long productId);

    @Query(value = "from Product p  where p.productStatus=true")
    List<Product> displayProductTrue();
}
