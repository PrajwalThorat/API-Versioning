package com.stackroute.controller.V2;

import com.stackroute.domain.Blog;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.service.V2.BlogServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provide implementation for Controller Class to access the REST API's
 */
@RestController
@RequestMapping(value = "/api/v2")
public class BlogControllerV2 {

    private BlogServiceV2 blogServiceV2;
    @Autowired
    public BlogControllerV2(BlogServiceV2 blogServiceV2){
        this.blogServiceV2 = blogServiceV2;
    }
    /**
     * Write implementation code for saving a blog method
     */
    @PostMapping("/blog")
    public ResponseEntity<BlogV2> saveBlog(@RequestBody BlogV2 blogV2) {
        BlogV2 savedBlog = blogServiceV2.saveBlog(blogV2);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }
    /**
     * Write implementation code for listing all blogs method
     */
    @GetMapping("/blogs")
    public ResponseEntity<List<BlogV2>> getAllBlogs() {
        return new ResponseEntity<List<BlogV2>>((List<BlogV2>) blogServiceV2.getAllBlogs(), HttpStatus.OK);

    }
    /**
     * Write implementation code for deleting a blog by id  method
     */

    @GetMapping("blog/{blogId}")
    public ResponseEntity<BlogV2> getBlogById(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<>(blogServiceV2.getBlogById(blogId), HttpStatus.FOUND);
    }
    /**
     * Write implementation code for fetching a blog by id method
     */
    @DeleteMapping("blog/{blogId}")
    public ResponseEntity<BlogV2> getBlogAfterDeleting(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<>(blogServiceV2.deleteBlog(blogId), HttpStatus.OK);
    }
    /**
     * Write implementation code for updating a blog method
     */
    @PutMapping("blog")
    public ResponseEntity<BlogV2> updateBlog(@RequestBody BlogV2 blogV2) {
        BlogV2 updatedBlog = blogServiceV2.updateBlog(blogV2);
        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }
}
