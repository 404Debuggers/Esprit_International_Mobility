package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;


@RestController

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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
        return publicationService.UpdatePost(id, post, id);
    }

    @DeleteMapping("/delp/{id}")

    public void deletePost(@PathVariable("id") Long id) {
        publicationService.deletePublication(id);
    }





    @PostMapping("/POST")
    public Post AddPost(@RequestBody Post post , Principal principal) throws Exception {
        return publicationService.addPostWithUser(post, principal);

    }
    @PutMapping("/LikePost/{idPost}")
    public void LikePost(@PathVariable("idPost") int idPost, Principal principal) {
        publicationService.likeAPost(idPost, principal);
    }

    @PutMapping("/DisLikePost/{idPost}")
    public void DisLikePost(@PathVariable("idPost") int idPost, Principal principal) {
        publicationService.DislikeAPost(idPost, principal);
    }



    @GetMapping("/bestPost")
    public BestPost bestPost() {
        return publicationService.best();
    }
    @GetMapping("/posts/{postId}/likes")
    public int countLikesForPost(@PathVariable("postId") long postId) {
        return publicationService.countLikesForPost(postId);
    }
    @GetMapping("/posts/{postId}/Dislikes")
    public int countDislikeForPost(@PathVariable("postId") long postId) {
        return publicationService.countDislikesForPost(postId);
    }
}