package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Color;
import com.dsecurity.repository.IColorRepository;
import com.dsecurity.repository.IProductRepository;
import com.dsecurity.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ColorServiceIMPL implements IColorService<Color,Long> {

    @Autowired
    private IColorRepository iColorRepository;
    @Override
    public List<Color> getAll() {
        return iColorRepository.findAll();
    }

    @Override
    public Color save(Color color) {
        return iColorRepository.save(color);
    }

    @Override
    public void delete(Long id) {
        iColorRepository.deleteById(id);
    }

    @Override
    public Color getById(Long id) {
        try {
            return iColorRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }
}
