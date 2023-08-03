package com.dsecurity.repository;

import com.dsecurity.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
