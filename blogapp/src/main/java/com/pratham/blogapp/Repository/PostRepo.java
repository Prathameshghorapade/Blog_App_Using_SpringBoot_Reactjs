package com.pratham.blogapp.Repository;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post>findByUser(User user);
    List<Post>findByCategory(Category category);
}
