package com.pratham.blogapp.Controllers;

import com.pratham.blogapp.Payloads.ApiResponse;
import com.pratham.blogapp.Payloads.CommentDto;
import com.pratham.blogapp.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){

        CommentDto createComment= commentService.createComment(commentDto,postId);

        return  new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

         commentService.deleteComment(commentId);

        return  new ResponseEntity<ApiResponse>(new ApiResponse("Comment Delete Successfully ",true),HttpStatus.OK);

    }

}
