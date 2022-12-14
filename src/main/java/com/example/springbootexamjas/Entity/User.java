package com.example.springbootexamjas.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String email ;
    private String pwd ;
    private String fName ;
    private String lName ;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    private List<Project> projects ;
    @ManyToMany(mappedBy = "users")
    private List<Project> project;


}
