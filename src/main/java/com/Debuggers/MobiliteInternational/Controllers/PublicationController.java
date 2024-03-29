package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
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
    @ResponseBody
    public void addPost(@RequestBody Post post) {
        publicationService.addPublication(post);
        System.out.println("success");
    }

    @PutMapping("/UpdatePost/{id}")
    public Post UpdatePost(@PathVariable(name = "id") long id, @RequestBody Post post) {
        return publicationService.UpdatePost(id, post);
    }

    @DeleteMapping("/delp/{id}")

    public void deletePost(@PathVariable("id") Long id) {
        publicationService.deletePublication(id);
    }





    @PostMapping("/POST")
    public Post AddPost(@RequestBody Post post , Principal principal) throws Exception {
        return publicationService.addPostWithUser(post, principal);

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
