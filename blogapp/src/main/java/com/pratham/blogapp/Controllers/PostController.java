package com.pratham.blogapp.Controllers;

import com.pratham.blogapp.Payloads.ApiResponse;
import com.pratham.blogapp.Payloads.PostDto;
import com.pratham.blogapp.Payloads.PostResponse;
import com.pratham.blogapp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody  PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId) {
        PostDto createPost = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }


    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize) {
        PostResponse postResponse = postService.getPostsByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize) {

        PostResponse postResponse = postService.getPostByCategory(categoryId,pageNumber,pageSize);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }



    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
                                                    @RequestParam (value = "sortBy",defaultValue = "postId",required = false)String sortBy,
                                                    @RequestParam (value = "sortDir",defaultValue = "asc",required = false)String sortDir) {

        PostResponse postResponse = postService.getAllPosts( pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }




    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto posts = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(posts, HttpStatus.OK);

    }


    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){

        postService.deletePost(postId);

        return new  ApiResponse("Post Deleted Successfully",true);
    }


    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }



}
