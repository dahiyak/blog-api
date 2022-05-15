package com.hobby.blogapi.controllers;

import com.hobby.blogapi.payloads.ApiResponse;
import com.hobby.blogapi.payloads.PostDto;
import com.hobby.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long catId) {
        PostDto postDto1 = postService.createPost(postDto, catId, userId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId) {
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postDto = postService.getAllPosts();
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted", true), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(PostDto postDto, @PathVariable Long postId) {
        return new ResponseEntity<>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }
}
