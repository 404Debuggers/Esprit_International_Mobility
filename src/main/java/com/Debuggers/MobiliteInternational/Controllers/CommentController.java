package com.Debuggers.MobiliteInternational.Controllers;




import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CommentController {
    CommentService commentService;

    @GetMapping("/GET")
    public List<Comment> listdesComments()
    {
        return commentService.ListdesComment();
    }


    @PostMapping("/Comm/{id}/{idUser}")
    public Comment CommentPost(@RequestBody Comment comment ,@PathVariable(name = "id") long idPost , @PathVariable(name = "idUser") long idUser) throws IOException {
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






}