package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment Addcoment(Comment comment , long idPost , long idUser);
    public List<Comment> ListdesComment();
    public void Delete(long idComment);
    public void updateComment(long idComment , Comment comment );

    public Comment addCommentToComment(Long userId, Long parentCommentId, Comment newComment);


}
