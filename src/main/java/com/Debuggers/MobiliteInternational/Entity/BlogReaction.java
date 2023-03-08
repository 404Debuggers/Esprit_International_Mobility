package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BlogReaction implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reactionId;
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Blog blog;
}