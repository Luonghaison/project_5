package com.dsecurity.controller;

import com.dsecurity.model.Catalog;
import com.dsecurity.repository.ICataRepository;
import com.dsecurity.service.serviceIMPL.CataServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v7/CatalogController")

public class CataController {
    @Autowired
    private CataServiceIMPL cataServiceIMPL;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public List<Catalog> getAllCatalog() {
        return cataServiceIMPL.getAll();
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN') or hasRole('USER')")
    public List<Catalog> getAllCatalogTrue() {
        return cataServiceIMPL.getAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Catalog createCata(@RequestBody Catalog catalog) {
        return cataServiceIMPL.saveAndUpdate(catalog);
    }

    @PutMapping("/{editId}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Catalog updateCata(@PathVariable("editId") Long editId, @RequestBody Catalog catalog) {
        Catalog updateCata = cataServiceIMPL.getById(editId);
        updateCata.setCatalogName(catalog.getCatalogName());
        updateCata.setCatalogDescription(catalog.getCatalogDescription());
        return cataServiceIMPL.saveAndUpdate(updateCata);
    }

    @GetMapping("/{searchbyname}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN') or hasRole('USER')")
    public List<Catalog> catalogSearchByName(@PathVariable("searchbyname") String searchbyname) {
        return cataServiceIMPL.findByCatalogNameContaining(searchbyname);
    }

    @GetMapping("/searchById/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN') or hasRole('USER')")
    public Catalog catalogSearchById(@PathVariable("id") long id) {
        return cataServiceIMPL.findByCatalogId(id);
    }

    @DeleteMapping("/delete/{deleteid}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteCatalog(@PathVariable("deleteid") Long deleteid) {
        Catalog catalog = cataServiceIMPL.getById(deleteid);
        if (catalog!=null) {
            cataServiceIMPL.delete(deleteid);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok("Bạn đã xóa thành công");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/asc")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<List<Catalog>> sortCatalogByName(@PathVariable("asc") String asc) {
        List<Catalog> catalogList = cataServiceIMPL.sortCatalogByCatalogName(asc);
        return new ResponseEntity<>(catalogList, HttpStatus.OK);
    }

    @GetMapping("/des")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<List<Catalog>> sortCatalogByNameDes(@PathVariable("des") String des) {
        List<Catalog> catalogList = cataServiceIMPL.sortCatalogByCatalogName(des);
        return new ResponseEntity<>(catalogList, HttpStatus.OK);
    }
}
