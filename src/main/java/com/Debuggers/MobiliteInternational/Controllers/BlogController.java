package com.Debuggers.MobiliteInternational.Controllers;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.*;
import com.Debuggers.MobiliteInternational.Entity.Enum.Category;
import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import com.Debuggers.MobiliteInternational.Repository.BlogReactionRepository;
import com.Debuggers.MobiliteInternational.Repository.BlogRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.BlogReactionService;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.http.ResponseEntity;
import com.Debuggers.MobiliteInternational.Services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@RestController
@AllArgsConstructor
public class BlogController {

    BlogService blogService;
    UserService userService;
    BlogReactionService blogReactionService;
    
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final BlogReactionRepository blogReactionRepository;

    @GetMapping("/allblogs")
    public List<Blog> getAllblogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/getb/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }
    @PostMapping("/BLOG/{id}")
    public Blog AddPost(@RequestBody Blog blog, @PathVariable(name = "id") long idUser) {
        return blogService.addBlogWithUser(blog, idUser);

    }


    @PutMapping("/UpdateBlog/{id}")
    public Blog UpdateBlog(@PathVariable(name = "id") long id, @RequestBody Blog blog) throws IOException {
        return blogService.updateBlog(id, blog);
    }

    @DeleteMapping("/delb/{id}")
    @ResponseBody
    public void deleteBlog(@PathVariable("id") Long id){
        blogService.deleteBlog(id);
    }


    @PostMapping("/{blogId}/reaction/{userId}")
    public ResponseEntity<String> createBlogReaction(@PathVariable Long blogId,
                                                     @PathVariable Long userId,
                                                     @RequestParam(required = true) ReactionType reactionType) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);

        if (!optionalBlog.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid blog ID");
        }

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }

        Blog blog = optionalBlog.get();
        User user = optionalUser.get();

        Optional<BlogReaction> existingReaction = Optional.ofNullable(blogReactionRepository.findByUserAndBlog(user, blog));

        if (existingReaction.isPresent()) {
            // Si l'utilisateur a déjà réagi, mettez à jour la réaction existante avec la nouvelle valeur
            BlogReaction reaction = existingReaction.get();
            if (reaction.getReactionType() == reactionType) {
                blogReactionRepository.delete(reaction);
                return ResponseEntity.ok("Blog reaction deleted");
            } else {
                reaction.setReactionType(reactionType);
                blogReactionRepository.save(reaction);
                return ResponseEntity.ok("Blog reaction updated");
            }
        } else {
            // Si l'utilisateur n'a pas encore réagi, créez une nouvelle réaction
            BlogReaction reaction = new BlogReaction();
            reaction.setBlog(blog);
            reaction.setUser(user);
            reaction.setReactionType(reactionType);
            blogReactionRepository.save(reaction);
            return ResponseEntity.ok("Blog reaction created");
        }
    }
    @GetMapping("/blogs/category/{category}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable Category category) {
        List<Blog> blogs = blogRepository.findByCategory(category);
        return ResponseEntity.ok(blogs);
    }
    @GetMapping("/DynamiqueEtudiant")
    public BestAlumni BestUserByBlog() {
        return blogService.allumnaiDinamique();
    }

    @GetMapping("/blogs/{blogId}/rating")
    public ResponseEntity<Double> calculateAndReturnRatingForBlog(@PathVariable Long blogId) {
        Double rating = blogService.calculateRatingForBlog(blogId);
        if (rating == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rating);
    }



}

