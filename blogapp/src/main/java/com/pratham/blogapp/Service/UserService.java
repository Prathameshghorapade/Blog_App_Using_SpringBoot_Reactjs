package com.pratham.blogapp.Service;

import com.pratham.blogapp.Payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto user,Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);
    UserDto getUSerById(Integer id);

}
