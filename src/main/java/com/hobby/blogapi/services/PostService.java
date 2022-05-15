package com.hobby.blogapi.services;

import com.hobby.blogapi.entities.Category;
import com.hobby.blogapi.entities.PostEntity;
import com.hobby.blogapi.entities.UserEntity;
import com.hobby.blogapi.exceptions.ResourceNotFoundException;
import com.hobby.blogapi.payloads.PostDto;
import com.hobby.blogapi.repositories.CategoryRepository;
import com.hobby.blogapi.repositories.PostRepository;
import com.hobby.blogapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PostDto createPost(PostDto postDto, Long catId, Long userId) {

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "cat Id", catId));

        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);

        postEntity.setImageName("Default.png");
        postEntity.setAddedDate(new Date());
        postEntity.setUserEntity(user);
        postEntity.setCategory(category);

        PostEntity newPost = postRepository.save(postEntity);

        return modelMapper.map(newPost, PostDto.class);
    }


    public List<PostDto> getPostByUser(Long userId) {

        UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        List<PostEntity> postEntities = postRepository.findByUserEntity(user);

        List<PostDto> postDtos = postEntities
                .stream()
                .map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    public List<PostDto> getPostByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<PostEntity> postEntities = postRepository.findByCategory(category);

        List<PostDto> postDtos = postEntities.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    public PostDto getPostById(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    public void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        postRepository.delete(post);

    }

    public PostDto updatePost(PostDto postDto, @PathVariable Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        PostEntity savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

}
