package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BlogController {

    BlogService blogService;
    @GetMapping("/allblogs")
    public List<Blog> getAllblogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/getb/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping("/addb")
    public void addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
    }

    @PutMapping("/upb")
    public void updateBlog(@RequestBody Blog blog) {
        blogService.updateBlog(blog);
    }

    @DeleteMapping("/delb/{id}")
    @ResponseBody
    public void deleteBlog(@PathVariable("id") Long id){
        blogService.deleteBlog(id);
    }
}
