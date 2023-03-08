package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.Reply;
import com.Debuggers.MobiliteInternational.Services.Impl.ReplyServiceImpl;
import com.Debuggers.MobiliteInternational.Services.ReplyService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor

public class ReplyController {

    ReplyService replyService;

    ReplyServiceImpl replyServiceIm;
    @PostMapping("/add-to-comment/{userId}/{commentId}")
    public Reply addReplyToComment(@PathVariable Long userId, @PathVariable Long commentId, @RequestBody Reply newReply) {
        return replyService.addReplyToComment(userId, commentId, newReply);
    }
    @GetMapping("/GETReply")
    public List<Reply> replies()
    {
        return replyService.getAllReply();
    }
    @DeleteMapping("/DELETER/{id}")
    public void DELETEONE(@PathVariable long id)
    {
        replyService.deleteReply(id);
    }

    @PutMapping("/UpdateReply/{userId}")
    public Reply UpdateReply(@PathVariable("userId") long userId, @RequestBody Reply reply) throws IOException {
        return replyServiceIm.UpdateReply(userId,reply);
    }

}
