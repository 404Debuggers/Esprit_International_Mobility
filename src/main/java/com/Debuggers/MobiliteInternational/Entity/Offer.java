package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Offer implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long offerId;
    private Date dateDebut;
    private Date dateFin;
    private String Title;
    private String description ;
    private Date deadline;
    @Enumerated(EnumType.STRING)
    private StudyField fieldOfStudy;
    private String prerequis;
    private String image;
    private Double frais;
    private Boolean archive;

    @ManyToOne
    private University university;
    /*
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Candidacy candidacy;

     */

    @JsonIgnore

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private Set<Candidacy> candidacies ;





}
