package com.pratham.blogapp.Entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity

@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "users_name")

    private String name;

    @Column(name = "email_address")

    private String email;


    private String password;

    private String about;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();

}
