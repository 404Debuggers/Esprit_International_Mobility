package com.Debuggers.MobiliteInternational.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public class Reply implements Serializable {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long replyId;
        private String description;
        @Temporal(TemporalType.DATE)
        private Date date;
        @ManyToOne
        @JsonIgnore
        private User user;
        @ManyToOne
        @JsonIgnore
        private Comment comment;
    }

