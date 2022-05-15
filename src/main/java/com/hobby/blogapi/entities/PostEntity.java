package com.hobby.blogapi.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

}
