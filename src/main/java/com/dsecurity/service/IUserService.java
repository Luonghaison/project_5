package com.dsecurity.service;

import com.dsecurity.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAllUser();
    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String username);

    Optional<User> findByUserId(Long id);
    User save(User user);
}
