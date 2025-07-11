package com.pratham.blogapp.Controllers;

import com.pratham.blogapp.Entity.User;
import com.pratham.blogapp.Payloads.ApiResponse;
import com.pratham.blogapp.Payloads.UserDto;
import com.pratham.blogapp.Service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUSerDto =userService.createUser(userDto);
        return new  ResponseEntity<>(createUSerDto, HttpStatus.CREATED);

    }



    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id){

        UserDto updateUSer =userService.updateUser(userDto,id);
        return new  ResponseEntity<>(updateUSer, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer id){

         userService.deleteUser( id);

         return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succesfully",true), HttpStatus.OK);

    }

       @GetMapping()
    public ResponseEntity<List<UserDto>>getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
       }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUSerById(id));
    }




}
