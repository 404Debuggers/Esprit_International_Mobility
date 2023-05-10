package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CommentRepository;
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Security.Services.UserDetailsImpl;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.security.Principal;

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
    public Post UpdatePost(long idPost, Post post,Long userId) {
        User user = userRepository.findById(userId).orElse(null);
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
    public Post addPostWithUser(Post post, Principal principal) throws IOException {

        User user = userRepository.findByEmail(principal.getName());

        System.out.println(user); // print user object to console
        post.setUser(user);
        System.out.println(post.getUser()); // print post user object to console
        user.getPosts().add(post);


        post.setDescription(PostUtils.filterBadWords(post.getDescription()));
        post.setTitle(PostUtils.filterBadWords(post.getTitle()));
        return publicationRepository.save(post);

    }

    @Override
    public void likeAPost(long idPost, Principal principal) {
        String email = principal.getName(); // Récupération de l'email de l'utilisateur connecté
        User user = userRepository.findByEmail(email);

        Post post = publicationRepository.findById(idPost).orElse(null);
        Set<User> likes = post.getUserLikes();
        Set<User> dislikes = post.getUserDislikes();
        int nbrLikes = post.getNbrLike(); // Récupération du nombre de likes actuel
        int nbrDislikes = post.getNbrDislike(); // Récupération du nombre de dislikes actuel

        if (dislikes.contains(user)) {
            dislikes.remove(user);
            nbrDislikes--; // Décrémentation du nombre de dislikes
        }

        if (!likes.contains(user)) {
            likes.add(user);
            nbrLikes++; // Incrémentation du nombre de likes
        } else {
            likes.remove(user);
            nbrLikes--; // Décrémentation du nombre de likes
        }

        post.setNbrLike(nbrLikes); // Mise à jour du nombre de likes
        post.setNbrDislike(nbrDislikes); // Mise à jour du nombre de dislikes
        publicationRepository.save(post);
    }





    @Override
    public void DislikeAPost(long idPost, Principal principal) {
        String email = principal.getName(); // Récupération de l'email de l'utilisateur connecté
        User user = userRepository.findByEmail(email);

        Post post = publicationRepository.findById(idPost).orElse(null);
        Set<User> likes = post.getUserLikes();
        Set<User> dislikes = post.getUserDislikes();
        int nbrLikes = post.getNbrLike(); // Récupération du nombre de likes actuel
        int nbrDislikes = post.getNbrDislike(); // Récupération du nombre de dislikes actuel

        if (likes.contains(user)) {
            likes.remove(user);
            nbrLikes--; // Décrémentation du nombre de likes
        }

        if (!dislikes.contains(user)) {
            dislikes.add(user);
            nbrDislikes++; // Incrémentation du nombre de dislikes
        } else {
            dislikes.remove(user);
            nbrDislikes--; // Décrémentation du nombre de dislikes
        }

        post.setNbrLike(nbrLikes); // Mise à jour du nombre de likes
        post.setNbrDislike(nbrDislikes); // Mise à jour du nombre de dislikes
        publicationRepository.save(post);
    }



    @Override
    public BestPost best() {
        return publicationRepository.best();
    }

    @Override
    public int countLikesForPost(long idPost) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        if (p == null) {
            return 0;
        } else {
            Set<User> l = p.getUserLikes();
            return l.size();
        }

    }

    @Override
    public int countDislikesForPost(long idPost) {
        Post p = publicationRepository.findById(idPost).orElseGet(null);
        if (p == null) {
            return 0;
        } else {
            Set<User> l = p.getUserDislikes();
            return l.size();
        }
    }


}