package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.BlogReaction;
import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.BlogReactionRepository;
import com.Debuggers.MobiliteInternational.Services.BlogReactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class BlogReactionServiceImpl implements BlogReactionService {

    private final BlogReactionRepository blogReactionRepository;
    @Override
    public BlogReaction createBlogReaction(Blog blog, User user, ReactionType reactionType) {
        BlogReaction existingReaction = blogReactionRepository.findByUserAndBlog(user, blog);
        if (existingReaction != null) {
            existingReaction.setReactionType(reactionType);
            return blogReactionRepository.save(existingReaction);
        } else {
            BlogReaction blogReaction = new BlogReaction();
            blogReaction.setBlog(blog);
            blogReaction.setUser(user);
            blogReaction.setReactionType(reactionType);
            return blogReactionRepository.save(blogReaction);
        }
    }
}
