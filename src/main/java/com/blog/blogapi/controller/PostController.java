package com.blog.blogapi.controller;

import com.blog.blogapi.dto.PostRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.blog.blogapi.entity.Post;
import com.blog.blogapi.service.PostService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request, Authentication authentication) {
        // For now, we'll hardcode username. We'll add proper authentication later
    	String username = authentication.getName();
    	Post post = postService.createPost(request, username);
        return ResponseEntity.ok(post);
    }
    
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postService.getAllPosts(pageable);
        return ResponseEntity.ok(postPage.getContent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request, Authentication authentication) {
    	String username = authentication.getName();
    	Post post = postService.updatePost(id, request, username);
        return ResponseEntity.ok(post);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication authentication) {
    	String username = authentication.getName();
    	postService.deletePost(id, username);
        return ResponseEntity.ok("Post deleted successfully");
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }
}