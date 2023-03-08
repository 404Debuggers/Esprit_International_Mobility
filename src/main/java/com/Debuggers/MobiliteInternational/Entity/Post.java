package com.Debuggers.MobiliteInternational.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

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

    @ManyToMany
    @JsonIgnore
    private Set<User> userLikes;

    @ManyToMany
    @JsonIgnore
    private Set<User> userDislikes;
    @ManyToOne

    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment>commentSet;



}
