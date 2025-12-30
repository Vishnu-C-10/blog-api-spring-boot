package com.blog.blogapi.repository;

import com.blog.blogapi.entity.Post;
import com.blog.blogapi.entity.Category;
import com.blog.blogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findByAuthor(User author);
    
    List<Post> findByCategory(Category category);
    
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}