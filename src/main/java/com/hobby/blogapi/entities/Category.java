package com.hobby.blogapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", length = 10, nullable = false)
    private Long categoryId;

    @Column(name = "name", nullable = false)
    private String categoryName;

    @Column(name = "description", nullable = false)
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> postList = new ArrayList<>();
}
