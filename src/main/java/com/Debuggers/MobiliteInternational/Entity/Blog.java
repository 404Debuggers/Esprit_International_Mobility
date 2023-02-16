package com.Debuggers.MobiliteInternational.Entity;

import com.Debuggers.MobiliteInternational.Entity.Enum.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Blog implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long blogId;
    @Enumerated(EnumType.STRING)
    private Category category ;
    private String title;
    private String description;
    private Integer likes;
    private Integer dislikes;
    private String image;
    private  Boolean archive;
    @ManyToOne (fetch = FetchType.LAZY)
    @JsonIgnore
    private User author;


}
