package com.likelion.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tutorials")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private Boolean published;

    @Override
    public String toString(){
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published +"]";
    }
}
