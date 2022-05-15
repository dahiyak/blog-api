package com.hobby.blogapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "User_Name", nullable = false,length = 12)
    private String name;

    @Column(name = "User_Email",nullable = false,length = 20)
    private String email;

    @Column(name = "User_Password")
    private String password;

    @Column(name = "User_About")
    private String about;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> postList = new ArrayList<>();

}
