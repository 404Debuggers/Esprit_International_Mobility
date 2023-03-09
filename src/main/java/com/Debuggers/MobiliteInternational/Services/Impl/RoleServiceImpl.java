import com.Debuggers.MobiliteInternational.Entity.Enum.ERole;
import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Repository.RoleRepository;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;
import com.Debuggers.MobiliteInternational.Services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {



    RoleRepository roleRepository;

    @Override
    public Role save(ERole roleName) {

        Optional<Role> existeRole = roleRepository.findByNom(roleName);
        if (existeRole.isPresent()){
            return existeRole.get();
        }
        else {
            Role role = new  Role();
            role.setNom(roleName);
            return   roleRepository.save(role);

        }

    }

    @Override
    public Role update(Role role) {

        Optional <Role> existeRole = roleRepository.findByNom(role.getNom());
        if (existeRole.isPresent()){
            return existeRole.get();

        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {

        Role role = findById(id);
        if (role==null){
        }
        roleRepository.delete(role);
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