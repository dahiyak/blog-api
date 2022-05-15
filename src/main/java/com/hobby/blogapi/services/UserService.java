package com.hobby.blogapi.services;

import com.hobby.blogapi.entities.UserEntity;
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
        UserEntity userEntity = dtoToUser(userDto);
        System.out.println(userEntity.getName());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userToDto(savedUserEntity);
    }

    public UserDto getUserById(Long id) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", id));
        return userToDto(userEntity);
    }

    public UserDto updateUser(UserDto userDto, Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", id));

        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        userRepository.save(userEntity);
        return userToDto(userEntity);
    }

    public List<UserDto> getAllUser() {
        List<UserEntity> userEntityList = new ArrayList<>();

        userRepository.findAll().forEach(userEntityList::add);

        List<UserDto> userDtoList = userEntityList.stream().map(userEntity -> userToDto(userEntity)).collect(Collectors.toList());
        return userDtoList;
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", id));
        userRepository.delete(userEntity);

    }

    private UserEntity dtoToUser(UserDto userDto) {
//        UserEntity userEntity = new UserEntity();
//
//        userEntity.setUserId(userDto.getUserId());
//        userEntity.setName(userDto.getName());
//        userEntity.setPassword(userDto.getPassword());
//        userEntity.setEmail(userDto.getEmail());
//        userEntity.setAbout(userDto.getAbout());
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    private UserDto userToDto(UserEntity userEntity) {
//        UserDto userDto = new UserDto();
//        userDto.setUserId(userEntity.getUserId());
//        userDto.setName(userEntity.getName());
//        userDto.setEmail(userEntity.getEmail());
//        userDto.setPassword(userEntity.getPassword());
//        userDto.setAbout(userEntity.getAbout());

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }
}
