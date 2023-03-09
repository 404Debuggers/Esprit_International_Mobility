package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;

import java.util.List;

public interface RoleService {

    public Role save(ERole rolename);
    public Role update(Role role);
    public void delete(Long id);
    public List<Role> findAllRoles();
    public Role findById(Long id);
}
