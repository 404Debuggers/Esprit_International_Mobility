package com.Debuggers.MobiliteInternational.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
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
    @GetMapping("/get")
    public List<Post> getAllPosts() {
        return publicationService.getAllPublication();
    }

    @GetMapping("/posti/{id}")
    public Post getPostById(@PathVariable Long id) {
        return publicationService.getPublicationById(id);
    }

    @PostMapping("/add")
    public void addPost(@RequestBody Post post) {
        publicationService.addPublication(post);
    }

    @PutMapping("/up")
    public void updatePost(@RequestBody Post post) {
        publicationService.updatePublication(post);
    }

    @DeleteMapping("/delp/{id}")

    public void deletePost(@PathVariable("id") Long id) {
        publicationService.deletePublication(id);
    }
}
