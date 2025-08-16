package com.pratham.blogapp.Controllers;

import com.pratham.blogapp.Config.AppConstants;
import com.pratham.blogapp.Payloads.ApiResponse;
import com.pratham.blogapp.Payloads.PostDto;
import com.pratham.blogapp.Payloads.PostResponse;
import com.pratham.blogapp.Service.FileService;
import com.pratham.blogapp.Service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
                                                      @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize) {
        PostResponse postResponse = postService.getPostsByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,@RequestParam (value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
                                                          @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize) {

        PostResponse postResponse = postService.getPostByCategory(categoryId,pageNumber,pageSize);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }



    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                    @RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                    @RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir) {

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


    @GetMapping("/post/search/{keyWord}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable ("keyWord") String keyWord) {
        List<PostDto> result = postService.searchPost(keyWord);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

    }


    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

     // post img upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto>  uploadPostImage(@RequestParam("image")MultipartFile image ,
                                                     @PathVariable Integer postId) throws IOException {

        PostDto postDto =postService.getPostById(postId);

        String fileName = fileService.uploadImage(path,image);

        postDto.setImageName(fileName);

        PostDto updatePOst = postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(updatePOst,HttpStatus.OK);

    }



    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void serveImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }





}
