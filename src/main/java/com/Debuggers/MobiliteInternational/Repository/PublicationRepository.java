package com.Debuggers.MobiliteInternational.Repository;


import com.Debuggers.MobiliteInternational.Entity.BestPost;
import com.Debuggers.MobiliteInternational.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PublicationRepository extends JpaRepository<Post,Long> {


    @Query(value = "SELECT description , COUNT( user_likes_post_id) occ FROM post_user_likes "
            + "LEFT JOIN post ON post.post_id=post_user_likes.user_likes_post_id "
            + "GROUP BY(user_likes_post_id)"
            + " ORDER BY  occ DESC "
            + " LIMIT 1" ,
            nativeQuery = true)
    public BestPost best();
}
