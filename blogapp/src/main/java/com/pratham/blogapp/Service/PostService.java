package com.pratham.blogapp.Service;

import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Payloads.PostDto;
import com.pratham.blogapp.Payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);


    //Update

    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete

    void deletePost(Integer postId);

    //GetAllPOsts

   PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);


    //Get Single Post

    PostDto getPostById(Integer postId);


    //get All PostsBy CAtegory
    PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);

    //get All Post by User

    PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize);

    //Search Post

    List<Post> searchPost(String keyWord);


    //search post by Title

    List<Post> searchByTitle(String title);

}
