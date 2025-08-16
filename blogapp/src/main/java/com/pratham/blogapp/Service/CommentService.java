package com.pratham.blogapp.Service;

import com.pratham.blogapp.Payloads.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
