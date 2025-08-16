package com.pratham.blogapp.Repository;

import com.pratham.blogapp.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
