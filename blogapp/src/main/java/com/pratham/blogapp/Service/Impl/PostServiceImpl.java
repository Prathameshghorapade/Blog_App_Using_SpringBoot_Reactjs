package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Entity.User;
import com.pratham.blogapp.Exceptions.ResourceNotFoundException;
import com.pratham.blogapp.Payloads.PostDto;
import com.pratham.blogapp.Repository.CategoryRepo;
import com.pratham.blogapp.Repository.PostRepo;
import com.pratham.blogapp.Repository.UserRepository;
import com.pratham.blogapp.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));


        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);

        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post getPOstById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));

       List<Post>posts=postRepo.findByCategory(category);

       List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId)

    {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
       List<Post> posts=postRepo.findByUser(user);
       List<PostDto>postDtos=posts.stream().map(post ->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<Post> searchPost(String keyWord) {
        return List.of();
    }

    @Override
    public List<Post> searchByTitle(String title) {
        return List.of();
    }
}
