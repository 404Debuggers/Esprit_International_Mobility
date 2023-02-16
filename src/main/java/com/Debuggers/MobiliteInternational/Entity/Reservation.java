package com.Debuggers.MobiliteInternational.Entity;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.Document;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reservationId;
    private String documents;
    private Date reservationDate;
    private Boolean archive;
    @ManyToOne
    private  Dormitories dorm;
    @OneToOne(fetch = FetchType.EAGER)
    private Candidacy candidacy;




}
