package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormType;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dormitories implements Serializable {
    @GeneratedValue
    @Id
    private Long DormitoriesId;
    @Enumerated(EnumType.STRING)
    private DormStatus Dormstatus;
    private Double rent;
    private Integer NbPlaces;
    private String location;
    private Float lattitude;
    private Float longitude;
    private boolean archive;
    private Float prix;
    @Enumerated(EnumType.STRING)
    private DormType dormType;

    private Double rating;

    @OneToMany (mappedBy = "dorm",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reservation>reservationSet;
    @ManyToOne
    @JsonIgnore
    private University university;
}
