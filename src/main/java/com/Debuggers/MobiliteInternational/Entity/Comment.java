package com.Debuggers.MobiliteInternational.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Integer likes;
    private Integer dislikes;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}
