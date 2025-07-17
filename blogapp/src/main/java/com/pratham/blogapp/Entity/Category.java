package com.pratham.blogapp.Entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Column(name = "title",nullable = false)
    private String categoryTitle;

    @Column(name = "description",nullable = false)
    private String categoryDescription;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Post>posts = new ArrayList<>();

}
