package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long offerId;
    private Date dateDebut;
    private Date dateFin;
    private String Title;
    private String description ;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private StudyField fieldOfStudy;
    private String prerequis;
    private String image;
    private Double frais;
    private Boolean archive;
    @ManyToOne
    private University university;
    @OneToMany(mappedBy = "offer")
    @JsonIgnore
    private Set<Candidacy> candidacySet;









}