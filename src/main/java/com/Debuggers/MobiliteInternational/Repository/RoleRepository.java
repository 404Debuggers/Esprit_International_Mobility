package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    //Optional<Role> findByNom(ERole roleName);

    Boolean existsByNom(ERole nom);
    Optional<Role> findByNom(ERole roleName);

}