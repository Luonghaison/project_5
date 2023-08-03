package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.CartItems;
import com.dsecurity.repository.ICartItemRepository;
import com.dsecurity.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class CartItemServiceIMPL implements ICartItemService<CartItems,Long> {
    @Autowired
    ICartItemRepository iCartItemRepository;
    @Override
    public CartItems save(CartItems cartItems) {
        return iCartItemRepository.save(cartItems);
    }

    @Override
    public void delete(Long id) {
        iCartItemRepository.deleteById(id);

    }

    @Override
    public CartItems findById(Long id) {
        return iCartItemRepository.findById(id).get();
    }

    @Override
    public List<CartItems> findCartItemByIdCart(Long id) {
        return null;
    }

    @Override
    public void deleteSetCart(Set<CartItems> set) {
        for (CartItems item: set
             ) {
            iCartItemRepository.delete(item);
        }
    }
}
