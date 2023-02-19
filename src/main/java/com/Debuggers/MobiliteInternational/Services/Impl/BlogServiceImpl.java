package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Repository.BlogRepository;
import com.Debuggers.MobiliteInternational.Services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;
    @Override
    public List<Blog> getAllBlogs() {
       return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void addBlog(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void updateBlog(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }
}
