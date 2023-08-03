package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Catalog;
import com.dsecurity.repository.ICataRepository;
import com.dsecurity.service.ICataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CataServiceIMPL implements ICataService<Catalog,Long> {

    @Autowired
    private ICataRepository iCataRepository;
    @Override
    public List<Catalog> getAll() {
        return iCataRepository.findAll();
    }
    @Override
    public Catalog saveAndUpdate(Catalog catalog) {
        return iCataRepository.save(catalog);
    }
    @Override
    public void delete(Long id) {
    iCataRepository.deleteById(id);
    }
    @Override
    public Catalog getById(Long id) {
        return iCataRepository.findById(id).get();
    }
    @Override
    public List<Catalog> findByCatalogNameContaining(String name) {
        return iCataRepository.findByCatalogNameContaining(name);
    }
    @Override
    public Catalog findByCatalogId(Long id) {
        return iCataRepository.findById(id).get();
    }
    @Override
    public List<Catalog> sortCatalogByCatalogName(String direction) {
        if (direction.equals("asc")){
            return iCataRepository.findAll(Sort.by("catalogName").ascending());
        }
        else{
            return iCataRepository.findAll(Sort.by("catalogName").descending());
        }
    }
    @Override
    public Page<Catalog> getPagging(Pageable pageable) {
        return iCataRepository.findAll(pageable);
    }
}