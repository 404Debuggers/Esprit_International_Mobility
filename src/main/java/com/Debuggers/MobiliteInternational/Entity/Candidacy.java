package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.Niveau;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long candidatureId;
    private String CoverLettre;
    private String attachements;
    private String B2Eng;
    private String B2Fr;
    @Enumerated(EnumType.STRING)
    private StudyField option;
    @Enumerated(EnumType.STRING)
    private Niveau levelEng;
    @Enumerated(EnumType.STRING)
    private Niveau levelFr;
    @Enumerated(EnumType.STRING)
    private Status status ;
    private double marks;
    private Boolean archive;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne
    private Offer offer;
    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

}