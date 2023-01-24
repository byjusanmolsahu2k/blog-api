package com.sample.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
    
    @Autowired//??
    private BlogRepo repo;

    @PostMapping("/addBlog")
    public String saveBlog(@RequestBody Blog blog)
    {
        repo.save(blog);
        return "Blog added successfully";
    }

    @GetMapping("/findAllBlogs")
    public List<Blog> getBlogs()
    {
        return repo.findAll();
    }

    @GetMapping("/findBlog/{id}")
    public Blog getBlog(@PathVariable String id)
    {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not exist with id: " + id));
    }

    @DeleteMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable String id)
    {
        repo.deleteById(id);
        return "Deleted successfully";
    }


    @PatchMapping("updateBlog/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable String id, @RequestBody Blog blog) {
        Blog blogToUpdate=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not exist with id: " + id));
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setImage(blog.getImage());
        blogToUpdate.setBody(blog.getBody());
        repo.save(blogToUpdate);
        return ResponseEntity.ok(blogToUpdate);
    }

}
