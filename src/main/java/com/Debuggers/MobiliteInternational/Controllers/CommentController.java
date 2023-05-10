package com.Debuggers.MobiliteInternational.Controllers;




import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/api/test")
public class CommentController {
    CommentService commentService;
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/GET")
    public List<Comment> listdesComments()
    {
        return commentService.ListdesComment();
    }


    @PostMapping("/Comm/{id}")
    public Comment CommentPost(@RequestBody Comment comment ,@PathVariable(name = "id") long idPost , Principal principal) throws IOException {
        return commentService.Addcoment(comment, idPost,principal);

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