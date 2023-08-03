package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Manufacturer;
import com.dsecurity.repository.IColorRepository;
import com.dsecurity.repository.IManufacturerRepository;
import com.dsecurity.service.IManufacturerSerrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ManufactorerIMPL implements IManufacturerSerrvice<Manufacturer,Long> {

    @Autowired
    private IManufacturerRepository iManufacturerRepository;

    @Override
    public List<Manufacturer> getAll() {
        return iManufacturerRepository.findAll();
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return iManufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(Long id) {
        iManufacturerRepository.deleteById(id);

    }

    @Override
    public Manufacturer getById(Long id) {
        try {
            return iManufacturerRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }
}
