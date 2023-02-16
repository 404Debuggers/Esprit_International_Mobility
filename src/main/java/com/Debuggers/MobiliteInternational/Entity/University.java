package com.Debuggers.MobiliteInternational.Entity;


import com.Debuggers.MobiliteInternational.Entity.Enum.Type;
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
public class University implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long universiteId;
    private String nameUniversite;
    private String location;
    private Float lattitude;
    private Float longitude;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "university",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Interview> interview;
    @OneToMany(mappedBy = "university",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Dormitories> dormitorieSet;
    @OneToMany(mappedBy = "university",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Offer> offerSet;


}
