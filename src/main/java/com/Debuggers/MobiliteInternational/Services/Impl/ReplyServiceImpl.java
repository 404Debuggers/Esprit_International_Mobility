package com.Debuggers.MobiliteInternational.Services.Impl;


import com.Debuggers.MobiliteInternational.Entity.Comment;
import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Entity.Reply;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CommentRepository;
import com.Debuggers.MobiliteInternational.Repository.ReplyRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    CommentRepository commentRepository;
    ReplyRepository replyRepository;
    UserRepository userRepository;

    @Override
    public  Reply addReplyToComment(Long userId, Long commentId, Reply newReply) {
        Optional<Comment> parentCommentOptional = commentRepository.findById(commentId);
        if (parentCommentOptional.isPresent()) {
            Comment parentComment = parentCommentOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                newReply.setUser(user);
                newReply.setComment(parentComment);
                newReply.setDate(new Date());
                return replyRepository.save(newReply);

            }

        }
        return null;
    }

    @Override
    public List<Reply> getAllReply() {
        return replyRepository.findAll();
    }

    @Override
    public Reply getReplyById(Long id) {
        return replyRepository.findById(id).orElse(null);
    }

    @Override
    public Reply UpdateReply(Long userId  , Reply reply ) throws IOException {
        User user = userRepository.findById(userId).orElse(null);
        reply.setDescription(reply.getDescription());
        reply.setDate(new Date());
        reply.setDescription(PostUtils.filterBadWords(reply.getDescription()));

        return replyRepository.save(reply) ;

    }

    @Override
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);

    }
}
