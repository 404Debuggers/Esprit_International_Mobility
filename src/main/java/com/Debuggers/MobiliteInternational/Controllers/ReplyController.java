package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Reply;
import com.Debuggers.MobiliteInternational.Services.ReplyService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class ReplyController {

ReplyService replyService;
    @PostMapping("/add-to-reply/{userId}/{commentId}")
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
    @PutMapping("/PUTR/{id}")
    public void UpdateReply(@PathVariable(name="id") long id , @RequestBody Reply reply )
    {
        replyService.UpdateReply(id, reply);
    }

}
