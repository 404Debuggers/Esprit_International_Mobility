package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;


import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class UserController {


    PublicationRepository publicationRepository;
    PublicationService publicationService;
    OfferRepository offerRepository;
    CandidacyRepository candidacyRepository;
    UserService userService;
    private final UserRepository userRepository;


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers(){


        return ResponseEntity.ok().body(userService.getAllUsers());
    }


    @PostMapping("/{idRole}")

    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user, @PathVariable("idRole") Long idRole){
        return ResponseEntity.created(null).body( userService.save(user,idRole));

    }


    @PutMapping("/users/{userId}")
    @ResponseBody
    public User updateUser(@PathVariable Long userId, @RequestBody User user){

        user.setUser_Id(userId);

        return userService.update(user);
    }


    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


    @PostMapping("/users/assigneRoleToUser/{idUser}/{roleName}")
    public ResponseEntity<?>addRoleToUser(@PathVariable("idUser") Long idUser , @PathVariable("roleName")Long roleId)
    {
        userService.assignRoleToUser(idUser, roleId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/profile/{email}")

    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }




    @GetMapping("/current-user/posts")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PARTNER')")

    public ResponseEntity<List<Post>> getCurrentUserPosts(Principal principal)
    {
        String email = principal.getName();
        List<Post> posts = publicationRepository.findByUserEmail(email);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/current-university/offers")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<List<Offer>> getCurrentUniversityOffer(Principal principal) {
        String email = principal.getName();

        List<Offer> offers = offerRepository.findByUniversityUserEmail(email);
        return ResponseEntity.ok(offers);



    }






    @GetMapping("/emails")
    public List<String> getAllUserEmails() {
        return userService.getAllUserEmails();
    }
}