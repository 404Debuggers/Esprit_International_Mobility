package com.Debuggers.MobiliteInternational.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Interview implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long interviewId;

    private String link;
    private Boolean archive;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidacy candidacy;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private University university;
    @OneToOne
    @JsonIgnore
    private Event event;
}
