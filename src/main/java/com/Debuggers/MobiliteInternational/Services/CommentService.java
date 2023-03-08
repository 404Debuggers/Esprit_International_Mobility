package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Comment;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    public Comment Addcoment(Comment comment , long idPost , long idUser) throws IOException;
    public List<Comment> ListdesComment();
    public void Delete(long idComment);
    public Comment updateComment(Long userId , Comment comment  ) throws IOException;




}
