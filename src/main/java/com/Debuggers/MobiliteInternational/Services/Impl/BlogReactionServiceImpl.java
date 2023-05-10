package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.BlogReaction;
import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.BlogReactionRepository;
import com.Debuggers.MobiliteInternational.Repository.BlogRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.BlogReactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor

public class BlogReactionServiceImpl implements BlogReactionService {

    private final BlogReactionRepository blogReactionRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;


    @Override
    public BlogReaction createBlogReaction(Blog blog, Principal principal, ReactionType reactionType) {
        User user = userRepository.findByEmail(principal.getName());
        BlogReaction existingReaction = blogReactionRepository.findByUserAndBlog(user, blog);
        if (existingReaction != null) {
            if (existingReaction.getReactionType() == reactionType) {
                // Si l'utilisateur a déjà réagi avec la même valeur, on ne fait rien
                return existingReaction;
            } else {
                // Si l'utilisateur a déjà réagi avec une valeur différente, on met à jour la réaction existante avec la nouvelle valeur
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
                return blogReactionRepository.save(existingReaction);
            }
        } else {
            // Si l'utilisateur n'a pas encore réagi, on crée une nouvelle réaction
            BlogReaction blogReaction = new BlogReaction();
            blogReaction.setBlog(blog);
            blogReaction.setUser(user);
            blogReaction.setReactionType(reactionType);
            if (reactionType == ReactionType.LOVE) {
                blog.setNbrlove(blog.getNbrlove() + 1);
            } else if (reactionType == ReactionType.LIKE) {
                blog.setNbrjaime(blog.getNbrjaime() + 1);
            } else if (reactionType == ReactionType.DISLIKE) {
                blog.setNbrjaimepas(blog.getNbrjaimepas() + 1);
            }
            blogRepository.save(blog);
            return blogReactionRepository.save(blogReaction);
        }
    }



}

