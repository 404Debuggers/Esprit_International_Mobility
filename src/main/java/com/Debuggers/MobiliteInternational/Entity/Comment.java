package com.Debuggers.MobiliteInternational.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne

    private User user;
    @ManyToOne
    @JsonIgnore
    private Post post;

    @OneToMany(mappedBy="comment",cascade = CascadeType.ALL)
    Set<Reply> reply;




}