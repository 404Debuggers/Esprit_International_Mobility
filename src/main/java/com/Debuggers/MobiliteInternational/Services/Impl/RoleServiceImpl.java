package com.Debuggers.MobiliteInternational.Services.Impl;


import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;
import com.Debuggers.MobiliteInternational.Services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {


    RoleRepository roleRepository;
    @Override
    public MessageResponse save(Role role) {

        boolean existe = roleRepository.existsByNom(role.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette nom existe déja !");
        }
        roleRepository.save(role);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Override
    public MessageResponse update(Role role) {

        boolean existe = roleRepository.existsById(role.getRoleId());
        if (!existe){
            boolean existe1 = roleRepository.existsByNom(role.getNom());
            return new MessageResponse(false,"Echec !","Cette role existe déja !");
        }
        roleRepository.save(role);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Override
    public MessageResponse delete(Long id) {

        Role role = findById(id);
        if (role==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        roleRepository.delete(role);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Role> findAllRoles() {

        return roleRepository.findAll();

    }

    @Override
    public Role findById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return role;
    }

}
