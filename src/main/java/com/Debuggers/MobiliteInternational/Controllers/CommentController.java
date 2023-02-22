package com.Debuggers.MobiliteInternational.Controllers;

<<<<<<< Updated upstream
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> Stashed changes
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

    @AllArgsConstructor
    public class CommentController {
        CommentService commentService;

    @GetMapping("/GET")
    public List<Comment> listdesComments()
    {
        return commentService.ListdesComment();
    }


    @PostMapping("/POST/{id}/{idUser}")
    public Comment CommentPost(@RequestBody Comment comment ,@PathVariable(name = "id") long idPost , @PathVariable(name = "idUser") long idUser)
    {
        return commentService.Addcoment(comment, idPost,idUser);

    }
    @DeleteMapping("/DELETE/{id}")
    public void DELETEONE(@PathVariable long id)
    {
        commentService.Delete(id);
    }
    @PutMapping("/PUT/{id}")
    public void UpdateComment(@PathVariable(name="id") long id , @RequestBody Comment comment )
    {
        commentService.updateComment(id, comment);
    }
    @PostMapping("/add-to-comment/{userId}/{parentCommentId}")
    public Comment addCommentToComment(@PathVariable Long userId, @PathVariable Long parentCommentId, @RequestBody Comment newComment) {
        return commentService.addCommentToComment(userId, parentCommentId, newComment);
    }





}
