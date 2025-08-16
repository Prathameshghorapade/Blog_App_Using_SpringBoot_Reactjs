package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Entity.Comment;
import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Exceptions.ResourceNotFoundException;
import com.pratham.blogapp.Payloads.CommentDto;
import com.pratham.blogapp.Repository.CommentRepo;
import com.pratham.blogapp.Repository.PostRepo;
import com.pratham.blogapp.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentId",commentId));

        commentRepo.delete(comment);

    }
}