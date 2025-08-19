package com.pratham.blogapp.Controllers;

import com.pratham.blogapp.Exceptions.ApiException;
import com.pratham.blogapp.Payloads.JwtAuthRequest;
import com.pratham.blogapp.Payloads.JwtAuthResponse;

import com.pratham.blogapp.Payloads.UserDto;
import com.pratham.blogapp.Security.JwtTokenHelper;
import com.pratham.blogapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    private void authenticate(String username, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details");
            throw new ApiException("Invalid UserName or Password");
        }
    }

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUSer(@RequestBody UserDto userDto){

        UserDto registeredUser = userService.registerUser(userDto);

        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {

        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails.getUsername());

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }





}



