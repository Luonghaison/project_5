package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Cart;
import com.dsecurity.repository.ICartRepository;
import com.dsecurity.repository.IUserRepository;
import com.dsecurity.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceIMPL implements ICartService<Cart,Long> {
    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public Cart save(Cart cart) {
        return iCartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
iCartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return iCartRepository.findById(id);
    }

    @Override
    public Cart findAllUserId(Long id) {
        return userRepository.findById(id).get().getCart();
    }
}
