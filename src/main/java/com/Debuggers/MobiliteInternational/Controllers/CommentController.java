package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Services.Impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController

@AllArgsConstructor
public class CommentController {
    CommentService commentService;
    CommentServiceImpl commentServiceImp;

    @GetMapping("/GET")
    public List<Comment> listdesComments() {
        return commentService.ListdesComment();
    }


    @PostMapping("/Comm/{id}/{idUser}")
    public Comment CommentPost(@RequestBody Comment comment, @PathVariable(name = "id") long idPost, @PathVariable(name = "idUser") long idUser) throws IOException {
        return commentService.Addcoment(comment, idPost, idUser);

    }

    @DeleteMapping("/DELETE/{id}")
    public void DELETEONE(@PathVariable long id) {
        commentService.Delete(id);
    }

    @PutMapping("/PUTc/{userId}")
    public Comment UpdateComment(@PathVariable("userId") long userId, @RequestBody Comment comment) throws IOException {
        return commentServiceImp.updateComment(userId, comment);


    }
}