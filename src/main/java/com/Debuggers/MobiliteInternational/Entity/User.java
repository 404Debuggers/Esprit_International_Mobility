package com.Debuggers.MobiliteInternational.Entity;


import com.Debuggers.MobiliteInternational.Entity.Enum.Sexe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long user_Id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date dateNaissance;
    @NotNull
    private String  phone;
    private String email;
    @Size(min = 6, message = "Length must be more than 6")
    private String password ;
    @NotNull
    private String adresse;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private String image;
    private Boolean Alumni;
    @OneToOne(fetch = FetchType.EAGER)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Candidacy> CandidacySet;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    @JsonIgnore
    private Set<Blog> blogSet;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Report> reportSet;


    @OneToMany
    private List<UserOfferFav> userFavOffers = new ArrayList<>();
}