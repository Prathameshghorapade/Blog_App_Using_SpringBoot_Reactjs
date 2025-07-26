package com.pratham.blogapp.Service;

import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Payloads.PostDto;
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

    List<PostDto> getAllPosts();


    //Get Single Post

    PostDto getPostById(Integer postId);


    //get All PostsBy CAtegory

    List<PostDto> getPostByCategory(Integer categoryId);

    //get All Post by User

    List<PostDto> getPostsByUser(Integer userId);

    //Search Post

    List<Post> searchPost(String keyWord);


    //search post by Title

    List<Post> searchByTitle(String title);

}
