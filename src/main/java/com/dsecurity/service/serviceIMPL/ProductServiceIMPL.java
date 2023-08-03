package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Catalog;
import com.dsecurity.model.Product;
import com.dsecurity.repository.ICataRepository;
import com.dsecurity.repository.IProductRepository;
import com.dsecurity.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceIMPL implements IProductService<Product,Long> {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Page<Product> getAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),5);
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Product saveAndUpdate(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public void delete(Long id) {
iProductRepository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        try {
            return iProductRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findByProductNameContaining(String name) {
        return iProductRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findByCatalog_CatalogId(Long catalogId) {
        return iProductRepository.findByCatalog_CatalogId(catalogId);
    }

    @Override
    public Catalog findCatalogByIdProduct(Long productId) {
        return iProductRepository.findCatalogByIdProduct(productId);
    }

    @Override
    public List<Product> displaProductTrue() {
        return iProductRepository.displayProductTrue();
    }
}
