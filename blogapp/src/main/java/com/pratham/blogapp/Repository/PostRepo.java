package com.pratham.blogapp.Repository;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Entity.Post;
import com.pratham.blogapp.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    Page<Post>  findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);

    List<Post>findByTitleContaining(String title);
}
