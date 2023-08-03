package com.dsecurity.controller;

import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.Color;
import com.dsecurity.model.Manufacturer;
import com.dsecurity.service.serviceIMPL.ManufactorerIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ManufactorerController")
public class ManufactorerController {
    @Autowired
    private ManufactorerIMPL manufactorerIMPL;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseMessage createManu(@RequestBody Manufacturer newManufactoer) {
        try {
            Manufacturer manufacturer = newManufactoer;
            ResponseMessage responseMessage = new ResponseMessage();
            manufacturer.setManuName(newManufactoer.getManuName());
            manufactorerIMPL.save(manufacturer);
//            return (ResponseEntity<Product>) ResponseEntity.ok();
            responseMessage.setMessage("Tao moi thanh cong");
            responseMessage.setStatus("200");
            responseMessage.setData(manufacturer);
            return responseMessage;

        }
        catch (Exception exception){
            System.out.printf("----" + exception.getMessage());
            return null;
        }
    }

    @PutMapping("/edit/{manuId}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Manufacturer updateManu(@PathVariable("manuId") Long manuId, @RequestBody Manufacturer manufacturer) {
        Manufacturer manuUpdate = manufactorerIMPL.getById(manuId);
        manuUpdate.setManuName(manufacturer.getManuName());

        return manufactorerIMPL.save(manuUpdate);
    }

    @DeleteMapping("/delete/{manuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> deleteManu(@PathVariable("manuId") Long manuId) {
        Manufacturer manufacturer = manufactorerIMPL.getById(manuId);
        if (manufacturer != null) {
            manufactorerIMPL.delete(manuId);
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
}
