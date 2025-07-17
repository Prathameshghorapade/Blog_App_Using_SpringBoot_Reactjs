package com.pratham.blogapp.Payloads;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private String title;

    private String content;

    private Date addedDate;

    private String imageName;

    private UserDto user;

    private CategoryDto category;

}
