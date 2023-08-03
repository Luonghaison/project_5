package com.dsecurity.repository;

import com.dsecurity.model.Catalog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICataRepository extends JpaRepository<Catalog,Long> {
    List<Catalog> findByCatalogNameContaining(String name);
//    Catalog (Long id);
}