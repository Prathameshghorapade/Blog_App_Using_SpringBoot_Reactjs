package com.pratham.blogapp.Repository;

import com.pratham.blogapp.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
