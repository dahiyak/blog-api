package com.hobby.blogapi.repositories;

import com.hobby.blogapi.entities.Category;
import com.hobby.blogapi.entities.PostEntity;
import com.hobby.blogapi.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByCategory(Category category);

    List<PostEntity> findByUserEntity(UserEntity userEntity);

    @Query("select p from PostEntity p where p.title like :key")
    List<PostEntity> searchByTitle(@Param("key") String keyword);


//    List<PostEntity> findByTitleContaining(String keyword);
}
