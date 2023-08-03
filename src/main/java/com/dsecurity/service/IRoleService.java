package com.dsecurity.service;

import com.dsecurity.model.Role;
import com.dsecurity.model.RoleName;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> findByName(RoleName name);


}
