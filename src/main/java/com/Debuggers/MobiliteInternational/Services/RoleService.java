package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;

import java.util.List;

public interface RoleService {

    public MessageResponse save(Role role);
    public MessageResponse update(Role role);
    public MessageResponse delete(Long id);
    public List<Role> findAllRoles();
    public Role findById(Long id);
}
