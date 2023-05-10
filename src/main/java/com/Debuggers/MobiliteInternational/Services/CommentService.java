package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Comment;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface CommentService {
    public Comment Addcoment(Comment comment , long idPost , Principal principal) throws IOException;
    public List<Comment> ListdesComment();
    public void Delete(long idComment);
    public void updateComment(long idComment , Comment comment );




}