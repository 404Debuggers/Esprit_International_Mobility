package com.Debuggers.MobiliteInternational.Request;

import com.Debuggers.MobiliteInternational.Entity.*;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class SignupRequest {

    @NotNull
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(length=50)
    private String firstName;

    @NotNull
    @Column(length=50)
    private String password;

    @NotNull
    @Column(length=50)
    private String lastName;
    @NotNull
    private Date DateNaissance;
    @NotNull
    @Column(length=50)
    private String adresse;
    private String phone;

    @NotNull
    @Column(length=8)
    private Long cin;

    private Set<String> role;

    private Set<Post> posts;

    private Set<Candidacy> candidacies;

    private Set<Comment> comments;

    private Set<Blog> blogs;

    private Set<Report> reports;

    private Set<UserOfferFav> userOfferFavs;


    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(Set<Candidacy> candidacies) {
        this.candidacies = candidacies;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<UserOfferFav> getUserOfferFavs() {
        return userOfferFavs;
    }

    public void setUserOfferFavs(Set<UserOfferFav> userOfferFavs) {
        this.userOfferFavs = userOfferFavs;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public Long getCin() {
        return cin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.DateNaissance = dateNaissance;
    }


    public String getPassword(){
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public Set<String> getRole()
    {
        return this.role;

    }

    public void setRole(Set<String> role)
    {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
