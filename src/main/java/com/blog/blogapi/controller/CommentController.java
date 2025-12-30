package com.blog.blogapi.controller;

import org.springframework.security.core.Authentication;
import com.blog.blogapi.dto.CommentRequest;
import com.blog.blogapi.entity.Comment;
import com.blog.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody CommentRequest request, Authentication authentication) {
        String username = authentication.getName();
        Comment comment = commentService.addComment(postId, request, username);
        return ResponseEntity.ok(comment);
    }
    
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Authentication authentication) {
    	String username = authentication.getName();
    	commentService.deleteComment(commentId, username);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}