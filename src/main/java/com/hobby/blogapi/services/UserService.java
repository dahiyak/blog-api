package com.hobby.blogapi.services;

import com.hobby.blogapi.entities.User;
import com.hobby.blogapi.payloads.UserDto;
import com.hobby.blogapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hobby.blogapi.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;


    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        System.out.println(user.getName());
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userToDto(user);
    }

    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        userRepository.save(user);
        return userToDto(user);
    }

    public List<UserDto> getAllUser() {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().forEach(userList::add);

        List<UserDto> userDtoList = userList.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);

    }

    private User dtoToUser(UserDto userDto) {
//        User user = new User();
//
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setUserId(user.getUserId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
