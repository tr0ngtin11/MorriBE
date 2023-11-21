package com.sample.demo.service;

import com.sample.demo.dto.BlogDTO;
import com.sample.demo.exception.BlogNotFoundException;
import com.sample.demo.model.Blog;
import com.sample.demo.model.Blog;
import com.sample.demo.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);


    public List<Blog> getAllBlog(){
        return  blogRepository.findAll();
    }

    public Optional<Blog> getBlogByID(Long id){
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isEmpty()){
            logger.error("Can't find any blog with Id: ");
        }
        return blog;
    }
    public Blog addBlog(BlogDTO blog){

        return blogRepository.save(new Blog(blog));
    }

    public Blog updateBlog(BlogDTO blog){
        Optional<Blog> blogUpdate = this.getBlogByID(blog.getBlogId());
        if(blogUpdate.isEmpty()){
            throw new BlogNotFoundException("Not found blog with id: " + blog.getBlogId());
        }
        return  blogRepository.save(blogUpdate.get());
    }

    public void deleteBlog(Long id)
    {
        Optional<Blog> blogUpdate = this.getBlogByID(id);
        if(blogUpdate.isEmpty()){
            throw new BlogNotFoundException("Not found blog with id: " + id);
        }
        blogRepository.deleteById(id);
    }


}
