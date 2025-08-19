package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Config.AppConstants;
import com.pratham.blogapp.Entity.Role;
import com.pratham.blogapp.Entity.User;
import com.pratham.blogapp.Payloads.UserDto;
import com.pratham.blogapp.Repository.RoleRepo;
import com.pratham.blogapp.Repository.UserRepository;
import com.pratham.blogapp.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.pratham.blogapp.Exceptions.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerUser(UserDto userDto) {

      User user =  modelMapper.map(userDto,User.class);

      //password encoding
       user.setPassword(passwordEncoder.encode(user.getPassword()));

       //role

        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
       User user = dtoToUser(userDto);
       User saved = userRepository.save(user);
        return userToDto(saved);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User updateUSer = userRepository.save(user);

        return userToDto(updateUSer);

    }

    @Override
    public List<UserDto> getAllUsers() {
        // 1. Fetch every User entity from the database
        List<User> users = userRepository.findAll();

        // 2. Convert each entity to a UserDto
        return users.stream()              // turn List<User> into a Stream<User>
                .map(this::userToDto)  // convert each User → UserDto
                .collect(Collectors.toList()); // back to List<UserDto>
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }

    @Override
    public UserDto getUSerById(Integer id) {

        User user =userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));

        return userToDto(user);
    }


    private User dtoToUser(UserDto userDto) {

       User user= modelMapper.map(userDto, User.class);

//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto userToDto(User user) {


        UserDto userDto = modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;

    }


}
