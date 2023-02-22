package com.Debuggers.MobiliteInternational.Controllers;

<<<<<<< Updated upstream
=======
import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> Stashed changes
import com.Debuggers.MobiliteInternational.Entity.Post;

import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PublicationController {

     PublicationService publicationService;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello post";
    }
    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts() {
        return publicationService.getAllPublication();
    }

    @GetMapping("/getPostById/{id}")
    public Post getPostById(@PathVariable Long id) {
        return publicationService.getPublicationById(id);
    }

    @PostMapping("/addPost")
    public void addPost(@RequestBody Post post) {
        publicationService.addPublication(post);
    }

    @PutMapping("/UpdatePost/{id}")
    public Post UpdatePost(@PathVariable(name = "id") long id, @RequestBody Post post) {
        return publicationService.UpdatePost(id, post);
    }

    @DeleteMapping("/delp/{id}")

    public void deletePost(@PathVariable("id") Long id) {
        publicationService.deletePublication(id);
    }





    @PostMapping("/POST/{id}")
    public Post AddPost(@RequestBody Post post, @PathVariable(name = "id") long idUser) {
        return publicationService.addPostWithUser(post, idUser);

    }
    @PutMapping("/LikePost/{idPost}/{id}")
    public void LikePost(@PathVariable("idPost") int idPost, @PathVariable("id") long id) {
        publicationService.likeAPost(idPost, id);}
    @PutMapping("/DisLikePost/{idPost}/{id}")
    public void DisLikePost(@PathVariable("idPost") int idPost, @PathVariable("id") long id) {
        publicationService.DislikeAPost(idPost, id);

    }
    @GetMapping("/bestPost")
    public BestPost bestPost() {
        return publicationService.best();
    }

}
