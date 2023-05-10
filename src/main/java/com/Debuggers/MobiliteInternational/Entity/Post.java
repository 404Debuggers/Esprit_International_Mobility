package com.Debuggers.MobiliteInternational.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long postId;
    private String description;
    private String title;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Boolean archive;
    private int nbrLike ;
    private int nbrDislike ;


    @ManyToMany
    private Set<User> userLikes;

    @ManyToMany
    @JsonIgnore
    private Set<User> userDislikes;
    @ManyToOne

    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment>commentSet;



}