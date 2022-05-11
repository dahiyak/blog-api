package com.hobby.blogapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;
    @NotEmpty(message = "Name is mandatory.")
    private String categoryName;
    @NotEmpty(message = "Description in mandatory.")
    private String categoryDescription;


}
