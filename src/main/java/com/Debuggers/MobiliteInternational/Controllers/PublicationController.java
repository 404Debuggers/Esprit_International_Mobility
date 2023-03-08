package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.BestPost;

import com.Debuggers.MobiliteInternational.Services.Impl.PublicationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
public class PublicationController {

    PublicationService publicationService;
    PublicationServiceImp publicationServiceImp;


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

    @PutMapping("/UpdatePost/{userId}")
    public Post UpdatePost(@PathVariable("userId") long userId, @RequestBody Post post) throws IOException {
        return publicationServiceImp.UpdatePost(post,userId);
    }

    @DeleteMapping("/delp/{id}")

    public void deletePost(@PathVariable("id") Long id) {
        publicationService.deletePublication(id);
    }





    @PostMapping("/POST/{id}")
    public Post AddPost(@RequestBody Post post, @PathVariable(name = "id") long idUser) throws Exception {
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
