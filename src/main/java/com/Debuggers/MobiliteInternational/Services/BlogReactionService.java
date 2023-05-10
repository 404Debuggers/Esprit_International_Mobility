package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.BlogReaction;
import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import com.Debuggers.MobiliteInternational.Entity.User;

import java.security.Principal;

public interface BlogReactionService {
    public BlogReaction createBlogReaction(Blog blog, Principal principal, ReactionType reactionType);
}