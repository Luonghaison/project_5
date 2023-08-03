package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Role;
import com.dsecurity.model.RoleName;
import com.dsecurity.repository.IRoleRepository;
import com.dsecurity.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceIMPL implements IRoleService {

    private final IRoleRepository roleService;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleService.findByName(name);
    }
}
