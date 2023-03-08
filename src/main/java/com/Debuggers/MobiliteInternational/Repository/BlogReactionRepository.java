package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.BlogReaction;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogReactionRepository extends JpaRepository<BlogReaction, Long> {
    BlogReaction findByUserAndBlog(User user, Blog blog);
    List<BlogReaction> findByBlogBlogId(Long blogId);
}