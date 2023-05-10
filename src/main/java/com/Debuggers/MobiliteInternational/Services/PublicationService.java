package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Entity.Post;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.security.Principal;
import java.util.List;

public interface PublicationService {
    public List<Post> getAllPublication();
    public Post getPublicationById(Long id);
    public void addPublication(Post post);

    public Post UpdatePost(long idPost , Post post,Long userId );
    public void deletePublication(Long id);

    public void deletePostLikes(long postId);
    public Post addPostWithUser(Post post, Principal principal) throws IOException;
    public void likeAPost(long idPost , Principal principal);
    public void DislikeAPost(long idPost , Principal principal);
    public BestPost best();
    public int countLikesForPost(long idPost);

    public int countDislikesForPost(long idPost);

}

