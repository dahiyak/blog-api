package com.hobby.blogapi.repositories;

import com.hobby.blogapi.entities.Category;
import com.hobby.blogapi.entities.PostEntity;
import com.hobby.blogapi.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findByCategory(Category category);

    List<PostEntity> findByUserEntity(UserEntity userEntity);

}
