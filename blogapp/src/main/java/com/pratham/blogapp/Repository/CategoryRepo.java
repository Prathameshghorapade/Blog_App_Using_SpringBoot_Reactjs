package com.pratham.blogapp.Repository;

import com.pratham.blogapp.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
