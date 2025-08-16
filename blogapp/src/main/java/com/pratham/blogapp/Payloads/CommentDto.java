package com.pratham.blogapp.Payloads;

import com.pratham.blogapp.Entity.Post;
import lombok.Data;

@Data
public class CommentDto {

    private int id;

    private String content;


}
