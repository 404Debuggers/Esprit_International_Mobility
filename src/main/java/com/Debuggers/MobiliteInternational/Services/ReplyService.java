package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.Reply;

import java.util.List;

public interface ReplyService {

    public   Reply addReplyToComment(Long userId, Long commentId, Reply newReply);
    public List<Reply> getAllReply();
    public Reply getReplyById(Long id);
    public Reply UpdateReply(long idComment , Reply reply );
    public void deleteReply(Long id);
}
