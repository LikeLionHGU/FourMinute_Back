package com.example.seazle.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    private String name;
    private String imageUrl;
    private String statement;


    @OneToMany(mappedBy="user", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Review> reviews= new ArrayList<>();

    @OneToMany(mappedBy="user", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Gather> gathers= new ArrayList<>();


    public User(String username, String password, String role, String name, String imageUrl, String statement) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.imageUrl = imageUrl;
        this.statement = statement;
    }
}
