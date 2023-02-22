package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CommentRepository;
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
<<<<<<< Updated upstream

=======
    private UserService userService;
>>>>>>> Stashed changes
    private CommentRepository commentRepository;
    PublicationRepository publicationRepository;
    UserRepository userRepository;


    @Override
    public Comment Addcoment(Comment comment, long idPost, long idUser) {
        comment.setPost(publicationRepository.findById(idPost).orElseGet(null));
        comment.setUser(userRepository.findById(idUser).orElseGet(null));
        comment.setDate(new Date());
        return commentRepository.save(comment)  ;
    }

    @Override
    public List<Comment> ListdesComment() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public void Delete(long idComment) {
        commentRepository.deleteById(idComment);

    }

    @Override
    public void updateComment(long idComment, Comment comment) {
        Comment c = commentRepository.findById(idComment).orElseGet(null);
        c.setDescription(comment.getDescription());
        c.setDate(comment.getDate());
        commentRepository.save(c);

    }

    @Override
    public Comment addCommentToComment(Long userId, Long parentCommentId, Comment newComment) {
        Optional<Comment> parentCommentOptional = commentRepository.findById(parentCommentId);
        if (parentCommentOptional.isPresent()) {
            Comment parentComment = parentCommentOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                newComment.setUser(user);
                newComment.setParentComment(parentComment);
                return commentRepository.save(newComment);
            }
        }
        return null;
    }
<<<<<<< Updated upstream
=======




>>>>>>> Stashed changes
}
