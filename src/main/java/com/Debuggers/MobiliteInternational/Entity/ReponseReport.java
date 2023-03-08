package com.Debuggers.MobiliteInternational.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReponseReport implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reponsetId;
    private String repdescription;

    @ManyToOne(fetch = FetchType.EAGER)

    private Report report;

}
