package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Entity.User;
import com.pratham.blogapp.Exceptions.ResourceNotFoundException;
import com.pratham.blogapp.Payloads.PostDto;
import com.pratham.blogapp.Payloads.PostResponse;
import com.pratham.blogapp.Repository.CategoryRepo;
import com.pratham.blogapp.Repository.PostRepo;
import com.pratham.blogapp.Repository.UserRepository;
import com.pratham.blogapp.Service.PostService;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Collator;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatePost=postRepo.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = postRepo.findAll(pageable);

        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos= allPosts.stream().map((post )->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse ;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        // Create Pageable object



        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // First, get the Category to ensure it exists
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        // Now fetch posts by category using a custom method in PostRepository
        Page<Post> pagePost = postRepo.findByCategory( category ,pageable);

        // Get content from Page
        List<Post> allPosts = pagePost.getContent();

        // Convert to DTOs
        List<PostDto> postDtos = allPosts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        // Prepare the response
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize)

    {


        Pageable pageable = PageRequest.of(pageNumber, pageSize);


        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
        Page<Post> pagePost  =postRepo.findByUser(user,pageable);

        List<Post> allPosts = pagePost.getContent();

       List<PostDto>postDtos=allPosts.stream().map(post ->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse ;
    }

    @Override
    public List<PostDto> searchPost(String keyWord) {
       List<Post> posts= postRepo.findByTitleContaining(keyWord);
            List<PostDto> postDtos=posts.stream().map((post)-> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos ;
    }

    @Override
    public List<Post> searchByTitle(String title) {
        return List.of();
    }
}
