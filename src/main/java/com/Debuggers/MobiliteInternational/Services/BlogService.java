package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.BestAlumni;
import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Entity.Post;

import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs();
    public   Blog getBlogById(Long id);
    public Blog addBlogWithUser(Blog blog, Long userId) ;


     public  Blog updateBlog(long idBlog, Blog blog);

    public  void deleteBlog(Long id);

    public BestAlumni allumnaiDinamique();
    public Double calculateRatingForBlog(Long blogId);
    public Double getRatingForBlog(Long blogId);
}
