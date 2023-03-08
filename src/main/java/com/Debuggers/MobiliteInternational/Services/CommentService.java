package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllComments();
    public   Comment getCommentById(Long id);
    public   void addComment(Comment comment);
    public   void updateComment(Comment comment);
    public  void deleteComment(Long id);
}
