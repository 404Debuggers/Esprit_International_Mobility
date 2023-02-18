package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

    @AllArgsConstructor
    public class CommentController {
        CommentService commentService;


        @GetMapping("/allcomment")
        public List<Comment> getAllComments() {
            return commentService.getAllComments();
        }

        @GetMapping("/getc{id}")
        public Comment getCommentById(@PathVariable Long id) {
            return commentService.getCommentById(id);
        }

        @PostMapping("/addc")
        public void addComment(@RequestBody Comment comment) {
            commentService.addComment(comment);
        }

        @PutMapping("/upc")
        public void updateComment(@RequestBody Comment comment) {
            commentService.updateComment(comment);
        }

        @DeleteMapping("/delc{id}")
        @ResponseBody
        public void deleteComment(@PathVariable("id") Long id){
            commentService.deleteComment(id);
        }
}
