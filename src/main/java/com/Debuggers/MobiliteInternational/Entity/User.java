package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.Sexe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long user_Id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private String  phone;
    private String email;
    private String password ;

    private String cin;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private String image;
    private Boolean Alumni;
    private boolean enabled;


    private String token;
    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles  = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Candidacy> CandidacySet;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnore
    private Set<Blog> blogSet;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Report> reportSet;
    @OneToMany
    private List<UserOfferFav> userFavOffers = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userLikes")
    @ToString.Exclude
    private Set<Post> userLikes=new HashSet<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userDislikes")
    @ToString.Exclude
    private Set<Post> userDislikes=new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ReponseReport> reponseReports ;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<University> universities;


    public User(String firstName, String lastName, Date dateNaissance, String phone, String email, String password, String adresse ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
    }

}

