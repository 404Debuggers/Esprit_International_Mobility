package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.Comment;

import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs();
    public   Blog getBlogById(Long id);
    public   void addBlog(Blog blog);
    public   void updateBlog(Blog blog);

    public  void deleteBlog(Long id);

}
