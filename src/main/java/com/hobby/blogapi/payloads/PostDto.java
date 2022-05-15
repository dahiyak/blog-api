package com.hobby.blogapi.payloads;


import com.hobby.blogapi.entities.Category;
import com.hobby.blogapi.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto userEntity;

}
