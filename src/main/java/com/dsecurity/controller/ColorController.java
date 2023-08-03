package com.dsecurity.controller;

import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.Color;
import com.dsecurity.model.Product;
import com.dsecurity.service.serviceIMPL.ColorServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ColorController")
public class ColorController {
    @Autowired
    private ColorServiceIMPL colorServiceIMPL;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseMessage createColor(@RequestBody Color newColor) {
        try {
            Color color = newColor;
            ResponseMessage responseMessage = new ResponseMessage();
            color.setColorName(newColor.getColorName());
            color.setColorStatus(newColor.isColorStatus());

            colorServiceIMPL.save(color);
//            return (ResponseEntity<Product>) ResponseEntity.ok();
            responseMessage.setMessage("Tao moi thanh cong");
            responseMessage.setStatus("200");
            responseMessage.setData(color);

            return responseMessage;

        }
        catch (Exception exception){
            System.out.printf("----" + exception.getMessage());
            return null;
        }
    }

    @PutMapping("/edit/{colorId}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Color updateProduct(@PathVariable("colorId") Long colorId, @RequestBody Color color) {
        Color colorUpdate = colorServiceIMPL.getById(colorId);
        colorUpdate.setColorName(color.getColorName());
        colorUpdate.setColorStatus(color.isColorStatus());
        return colorServiceIMPL.save(colorUpdate);
    }

    @DeleteMapping("/delete/{colorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable("colorId") Long colorId) {
        Color color = colorServiceIMPL.getById(colorId);
        if (color != null) {
            colorServiceIMPL.delete(colorId);
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
