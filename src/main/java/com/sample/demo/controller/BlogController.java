package com.sample.demo.controller;


import com.sample.demo.dto.BlogDTO;
import com.sample.demo.dto.ResponseStringDTO;
import com.sample.demo.exception.BlogNotFoundException;
import com.sample.demo.exception.ProductNotFoundException;
import com.sample.demo.model.Blog;
import com.sample.demo.repository.BlogRepository;
import com.sample.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;



    @GetMapping("/blog")
    public ResponseEntity<Object> getAllBlog(){
        List<Blog> listBlogs = blogService.getAllBlog();
        return ResponseEntity.ok(listBlogs);
    }


    @GetMapping("/blog/{id}")
    public ResponseEntity<Object> getBlogById(Long id){
        Optional<Blog> blog = blogService.getBlogByID(id);
        if(blog.isEmpty()){
            throw new BlogNotFoundException("Not found blog with id: " + id);
        }
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/blog")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> addBlog(@RequestBody BlogDTO blogDTO){
        Blog blog = blogService.addBlog(blogDTO);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/blog")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> editBlog(@RequestBody BlogDTO blogDTO){
        Blog blog = blogService.updateBlog(blogDTO);
        return ResponseEntity.ok(blog);
    }


    @DeleteMapping("/blog/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.ok(
        ResponseStringDTO.builder()
                .message("Deleted successfully")
                .build()
        );
    }


}
