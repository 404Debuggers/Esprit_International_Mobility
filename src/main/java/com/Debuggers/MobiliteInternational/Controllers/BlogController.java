package com.Debuggers.MobiliteInternational.Controllers;
import org.springframework.http.HttpStatus;
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
import java.security.Principal;
import java.util.List;
import java.util.Optional;



@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/test")

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
    @PostMapping("/BLOG")
    public Blog AddBlog(@RequestBody Blog blog, Principal principal) {
        return blogService.addBlogWithUser(blog, principal);

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


    @DeleteMapping("/{blogId}/reaction")
    public ResponseEntity<String> deleteBlogReaction(@PathVariable Long blogId, Principal principal) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);

        if (!optionalBlog.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid blog ID");
        }

        User user = userRepository.findByEmail(principal.getName());

        Blog blog = optionalBlog.get();

        Optional<BlogReaction> existingReaction = Optional.ofNullable(blogReactionRepository.findByUserAndBlog(user, blog));

        if (!existingReaction.isPresent()) {
            return ResponseEntity.badRequest().body("No reaction found for this user and blog");
        }

        BlogReaction reaction = existingReaction.get();
        if (reaction.getReactionType() == ReactionType.LOVE) {
            blog.setNbrlove(blog.getNbrlove() - 1);
        } else if (reaction.getReactionType() == ReactionType.LIKE) {
            blog.setNbrjaime(blog.getNbrjaime() - 1);
        } else if (reaction.getReactionType() == ReactionType.DISLIKE) {
            blog.setNbrjaimepas(blog.getNbrjaimepas() - 1);
        }
        blogRepository.save(blog);
        blogReactionRepository.delete(reaction);
        return ResponseEntity.ok("Blog reaction deleted");
    }

    public ResponseEntity<Blog> updateBlogReaction(@PathVariable Long id, @RequestParam("reactionType") ReactionType reactionType, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        Blog blog = blogRepository.findById(id).orElse(null);
        BlogReaction existingReaction = blogReactionRepository.findByUserAndBlog(user, blog);

        if (existingReaction != null) {
            ReactionType previousReactionType = existingReaction.getReactionType();
            if (previousReactionType == reactionType) {
                return ResponseEntity.ok(blog);
            } else {
                if (previousReactionType == ReactionType.LOVE) {
                    blog.setNbrlove(blog.getNbrlove() - 1);
                } else if (previousReactionType == ReactionType.LIKE) {
                    blog.setNbrjaime(blog.getNbrjaime() - 1);
                } else if (previousReactionType == ReactionType.DISLIKE) {
                    blog.setNbrjaimepas(blog.getNbrjaimepas() - 1);
                }
                existingReaction.setReactionType(reactionType);
            }
        } else {
            BlogReaction blogReaction = new BlogReaction();
            blogReaction.setBlog(blog);
            blogReaction.setUser(user);
            blogReaction.setReactionType(reactionType);
            blogReactionRepository.save(blogReaction);
        }

        if (reactionType == ReactionType.LOVE) {
            blog.setNbrlove(blog.getNbrlove() + 1);
        } else if (reactionType == ReactionType.LIKE) {
            blog.setNbrjaime(blog.getNbrjaime() + 1);
        } else if (reactionType == ReactionType.DISLIKE) {
            blog.setNbrjaimepas(blog.getNbrjaimepas() + 1);
        }
        blogRepository.save(blog);

        return ResponseEntity.ok(blog);
    }

    @PostMapping("/blogs/{blogId}/blogReactions")
    public ResponseEntity<?> createBlogReaction(@PathVariable Long blogId, @RequestParam("reactionType") ReactionType reactionType, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        Blog blog = blogRepository.findById(blogId).orElse(null);


        BlogReaction existingReaction = blogReactionRepository.findByUserAndBlog(user, blog);

        if (existingReaction != null) {
            if (existingReaction.getReactionType() == reactionType) {
                blogReactionRepository.delete(existingReaction);

                if (reactionType == ReactionType.LOVE) {
                    blog.setNbrlove(blog.getNbrlove() - 1);
                } else if (reactionType == ReactionType.LIKE) {
                    blog.setNbrjaime(blog.getNbrjaime() - 1);
                } else if (reactionType == ReactionType.DISLIKE) {
                    blog.setNbrjaimepas(blog.getNbrjaimepas() - 1);
                }

                blogRepository.save(blog);
                return ResponseEntity.ok("Blog reaction removed successfully");
            } else {
                if (existingReaction.getReactionType() == ReactionType.LOVE) {
                    blog.setNbrlove(blog.getNbrlove() - 1);
                } else if (existingReaction.getReactionType() == ReactionType.LIKE) {
                    blog.setNbrjaime(blog.getNbrjaime() - 1);
                } else if (existingReaction.getReactionType() == ReactionType.DISLIKE) {
                    blog.setNbrjaimepas(blog.getNbrjaimepas() - 1);
                }

                existingReaction.setReactionType(reactionType);

                if (reactionType == ReactionType.LOVE) {
                    blog.setNbrlove(blog.getNbrlove() + 1);
                } else if (reactionType == ReactionType.LIKE) {
                    blog.setNbrjaime(blog.getNbrjaime() + 1);
                } else if (reactionType == ReactionType.DISLIKE) {
                    blog.setNbrjaimepas(blog.getNbrjaimepas() + 1);
                }

                blogRepository.save(blog);
                blogReactionRepository.save(existingReaction);
                return ResponseEntity.ok("Blog reaction updated successfully");
            }
        } else {
            BlogReaction newReaction = new BlogReaction();
            newReaction.setUser(user);
            newReaction.setBlog(blog);
            newReaction.setReactionType(reactionType);

            if (reactionType == ReactionType.LOVE) {
                blog.setNbrlove(blog.getNbrlove() + 1);
            } else if (reactionType == ReactionType.LIKE) {
                blog.setNbrjaime(blog.getNbrjaime() + 1);
            } else if (reactionType == ReactionType.DISLIKE) {
                blog.setNbrjaimepas(blog.getNbrjaimepas() + 1);
            }

            blogRepository.save(blog);
            blogReactionRepository.save(newReaction);
            return ResponseEntity.ok("Blog reaction created successfully");
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

