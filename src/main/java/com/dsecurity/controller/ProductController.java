package com.dsecurity.controller;

import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.Catalog;
import com.dsecurity.model.Color;
import com.dsecurity.model.Manufacturer;
import com.dsecurity.model.Product;
import com.dsecurity.service.serviceIMPL.CataServiceIMPL;
import com.dsecurity.service.serviceIMPL.ColorServiceIMPL;
import com.dsecurity.service.serviceIMPL.ManufactorerIMPL;
import com.dsecurity.service.serviceIMPL.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ProductController")
public class ProductController {
    @Autowired
    private ProductServiceIMPL productServiceImp;
    @Autowired
    private CataServiceIMPL catalogServiceImp;

    @Autowired
    private ManufactorerIMPL manufactorerIMPL;

    @Autowired
    private ColorServiceIMPL colorServiceIMPL;


    @GetMapping("/getAll")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Page<Product> getAllProducts(Pageable pageable) {
        return productServiceImp.getAll(pageable);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public List<Product> displayProductTrue() {
        return productServiceImp.displaProductTrue();
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Product getByIdProduct(@PathVariable("productId") Long productId) {
        return productServiceImp.getById(productId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseMessage createProduct(@RequestBody Product newProduct) {
        try {
            Product product = newProduct;
            ResponseMessage responseMessage = new ResponseMessage();
            product.setProductName(newProduct.getProductName());
            product.setCreatDate(new Date());
            product.setPrice(newProduct.getPrice());
            product.setTitle(newProduct.getTitle());
            product.setProductImg(newProduct.getProductImg());
            product.setProductStatus(newProduct.isProductStatus());
            product.setProductName(newProduct.getProductName());
            Catalog catalog = catalogServiceImp.findByCatalogId(newProduct.getCatalog().getCatalogId());
            product.setCatalog(catalog);
            Manufacturer manufacturer = manufactorerIMPL.getById(newProduct.getManufacturer().getManuId());
            product.setManufacturer(manufacturer);
            Color color = colorServiceIMPL.getById(newProduct.getColor().getColorId());
            product.setColor(color);
            productServiceImp.saveAndUpdate(product);
//            return (ResponseEntity<Product>) ResponseEntity.ok();
            responseMessage.setMessage("Tao moi thanh cong");
            responseMessage.setStatus("200");
            responseMessage.setData(product);

            return responseMessage;

        }
        catch (Exception exception){
            System.out.printf("----" + exception.getMessage());
            return null;
        }
    }

    @PutMapping("/edit/{productId}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
        Product productUpdate = productServiceImp.getById(productId);
        productUpdate.setProductName(product.getProductName());
        productUpdate.setCreatDate(product.getCreatDate());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setTitle(product.getTitle());
        productUpdate.setProductImg(product.getProductImg());
        productUpdate.setProductDescription(product.getProductDescription());
        productUpdate.setProductStatus(product.isProductStatus());

        Catalog catalog = productServiceImp.findCatalogByIdProduct(productId);
        product.setCatalog(catalog);

        return productServiceImp.saveAndUpdate(productUpdate);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable("productId") Long productId) {
        Product product = productServiceImp.getById(productId);
        if (product != null) {
            productServiceImp.delete(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body(
                    ResponseMessage.builder()
                            .status("OK")
                            .message("không tìm thấy sản phẩm cần xóa")
                            .build()
            );
        }
    }

    @GetMapping("/search/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public List<Product> searchByName(@PathVariable("name") String name) {
        return productServiceImp.findByProductNameContaining(name);
    }
}
