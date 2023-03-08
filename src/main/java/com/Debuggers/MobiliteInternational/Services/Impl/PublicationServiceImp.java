package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.*;
import com.Debuggers.MobiliteInternational.Repository.CommentRepository;
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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


    @Override
    public void deletePostLikes(long postId) {

    }


    @Override
    public Post addPostWithUser(Post post, Long userId) throws IOException {

            User user = userRepository.findById(userId).orElse(null);
            post.setUser(user);
            user.getPosts().add(post);

        post.setDescription(PostUtils.filterBadWords(post.getDescription()));
        post.setTitle(PostUtils.filterBadWords(post.getTitle()));

            return publicationRepository.save(post);
        }


        @Override
    public void likeAPost(long idPost, long id) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        User e = userRepository.findById(id).orElseGet(null);
        Set<User> l = p.getUserLikes();
        if (p.getUserDislikes().contains(e)) {
            p.getUserDislikes().remove(e);
            l.add(e);
            p.getUserLikes();
        } else {
            if (p.getUserLikes().contains(e)) {
                p.getUserLikes().remove(e);
            } else {
                l.add(e);
            }
        }
        publicationRepository.save(p);
    }

    @Override
    public void DislikeAPost(long idPost, long id) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        User e = userRepository.findById(id).orElseGet(null);
        Set<User> l = p.getUserDislikes();
        if (p.getUserLikes().contains(e)) {
            p.getUserLikes().remove(e);
            l.add(e);
            p.getUserDislikes();
        } else {
            if (p.getUserDislikes().contains(e)) {
                p.getUserDislikes().remove(e);
            } else {
                l.add(e);
            }
        }
        publicationRepository.save(p);


    }

    @Override
    public BestPost best() {
        return publicationRepository.best();
    }








}






