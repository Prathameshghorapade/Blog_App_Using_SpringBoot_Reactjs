package com.pratham.blogapp.Security;

import com.pratham.blogapp.Entity.User;
import com.pratham.blogapp.Exceptions.ResourceNotFoundException2;
import com.pratham.blogapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Loading Users from Database using Emails

   User user = userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException2("user","email",username));

        return user;
    }
}
