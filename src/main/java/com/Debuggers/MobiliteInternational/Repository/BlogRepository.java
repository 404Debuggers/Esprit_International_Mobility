package com.Debuggers.MobiliteInternational.Repository;
import com.Debuggers.MobiliteInternational.Entity.BestAlumni;
import com.Debuggers.MobiliteInternational.Entity.Blog;
import com.Debuggers.MobiliteInternational.Entity.Enum.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    List<Blog> findByCategory(Category category);
    @Query(value = "SELECT first_name , user.last_name  , COUNT( blog.blog_id ) nbrdesBlog "
            + "FROM user  LEFT JOIN blog ON user.user_id = blog.author_user_id "
            + "GROUP BY(user.user_id) "
            + "ORDER BY nbrdesBlog DESC"
            + " LIMIT 1"  , nativeQuery = true)
    public BestAlumni AlumniDinamique();

}