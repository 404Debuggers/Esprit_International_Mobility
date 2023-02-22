package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Role;
import com.Debuggers.MobiliteInternational.Response.MessageResponse;
import com.Debuggers.MobiliteInternational.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping()
    /*@ApiOperation(value="trouver tous les roles", notes="find all roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="roles trouvés")
    })*/
    public List<Role> findAllRoles() {
        return roleService.findAllRoles();
    }

    @PostMapping
   /* @ApiOperation(value="enregistrer un role", notes="save organisme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role enregistré")
    })*/
    public MessageResponse save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping
   /* @ApiOperation(value="Mettre à jour un role", notes="update role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role mis à jour")
    })*/
    public MessageResponse update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @GetMapping("/{id}")
   /*  @ApiOperation(value="trouver un role par id", notes="find role par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role trouvé")
    })*/
    public Role findById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
  /*  @ApiOperation(value="Supprimer un role", notes="delete role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role supprimé")
    })*/
    public MessageResponse delete(@PathVariable Long id) {
        return roleService.delete(id);
    }
}
