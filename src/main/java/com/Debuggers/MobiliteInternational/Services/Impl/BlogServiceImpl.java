package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.BestAlumni;
import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.BlogReaction;
import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.BlogReactionRepository;
import com.Debuggers.MobiliteInternational.Repository.BlogRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;
    UserRepository userRepository;
    private final BlogReactionRepository blogReactionRepository;

    @Override
    public List<Blog> getAllBlogs() {
       return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog addBlogWithUser(Blog blog, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        blog.setAuthor(user);
        user.getBlogSet().add(blog);


        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(long idBlog, Blog blog) {
        Blog b = blogRepository.findById(idBlog).orElse(null);
        b.setDescription(blog.getDescription());
        b.setTitle(blog.getTitle());
        b.setCategory(blog.getCategory());


        return blogRepository.save(b);
    }


    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }
    @Override
    public BestAlumni allumnaiDinamique() {
        return blogRepository.AlumniDinamique();
    }

    @Override
    public Double calculateRatingForBlog(Long blogId) {
        List<BlogReaction> reactions = blogReactionRepository.findByBlogBlogId(blogId);
        Long loveCount = reactions.stream().filter(r -> r.getReactionType() == ReactionType.LOVE).count();
        Long likeCount = reactions.stream().filter(r -> r.getReactionType() == ReactionType.LIKE).count();
        Long dislikeCount = reactions.stream().filter(r -> r.getReactionType() == ReactionType.DISLIKE).count();
        Long totalCount = loveCount + likeCount + dislikeCount;
        if (totalCount == 0) {
            return null;
        }
        Double rating = ((double) loveCount + ((double) likeCount / 2)) / totalCount;
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            blog.setRating(rating);
            blogRepository.save(blog);
        }
        return rating;
    }

    @Override
    public Double getRatingForBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            return null;
        }
        return blog.getRating();
    }

}
