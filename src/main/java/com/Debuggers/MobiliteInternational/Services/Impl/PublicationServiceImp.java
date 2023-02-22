package com.Debuggers.MobiliteInternational.Services.Impl;
<<<<<<< Updated upstream

import com.Debuggers.MobiliteInternational.Entity.Post;
=======
import com.Debuggers.MobiliteInternational.Entity.*;
import com.Debuggers.MobiliteInternational.Repository.CommentRepository;
>>>>>>> Stashed changes
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements PublicationService {
    private final PublicationRepository publicationRepository;
    UserRepository userRepository;
    CommentRepository commentRepository;


    @Override
    public List<Post> getAllPublication() {
        return publicationRepository.findAll();
    }

    @Override
    public Post getPublicationById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public void addPublication(Post post) {
        publicationRepository.save(post);
    }

    @Override
    public Post UpdatePost(long idPost, Post post) {
        Post p = publicationRepository.findById(idPost).orElse(null);
        p.setDescription(post.getDescription());
        p.setTitle(post.getTitle());



        return publicationRepository.save(p);
    }


    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
<<<<<<< Updated upstream
=======



    @Override
    public void deletePostLikes(long postId) {

    }


    @Override
        public Post addPostWithUser(Post post, Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        post.setUser(user);
        user.getPosts().add(post);

      return   publicationRepository.save(post);
    }


    @Override
    public void likeAPost(long idPost, long id) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        User e = userRepository.findById(id).orElseGet(null);
        Set<User> l = p.getUserLikes();
        if(p.getUserDislikes().contains(e))
        {
            p.getUserDislikes().remove(e);
            l.add(e);
            p.getUserLikes();
        }
        else
        {	if(p.getUserLikes().contains(e)) {
            p.getUserDislikes().remove(e);
        }
        else {l.add(e);}
        }
        publicationRepository.save(p);
    }

    @Override
    public void DislikeAPost(long idPost, long id) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        User e = userRepository.findById(id).orElseGet(null);
        Set<User> l = p.getUserDislikes();
        if(p.getUserLikes().contains(e))
        {
            p.getUserLikes().remove(e);
            l.add(e);
            p.getUserDislikes();
        }
        else
        {	if(p.getUserDislikes().contains(e)) {
            p.getUserDislikes().remove(e);
        }
        else {l.add(e);}
        }
        publicationRepository.save(p);


    }

    @Override
    public BestPost best() {
        return publicationRepository.best();
    }
>>>>>>> Stashed changes
}



