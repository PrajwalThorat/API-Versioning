package com.stackroute.service.V2;

import com.stackroute.domain.Blog;
import com.stackroute.domain.V2.BlogV2;

import java.util.List;

/**
 * This is a BlogServiceV2 Interface and it should list all the methods to access REST API's
 */
public interface BlogServiceV2 {
    /**
     * AbstractMethod to save a blog
     */
    BlogV2 saveBlog(BlogV2 blogV2);

    /**
     * AbstractMethod to get all blogs
     */
    List<BlogV2> getAllBlogs();

    /**
     * AbstractMethod to get blog by id
     */
    BlogV2 getBlogById(int id);

    /**
     * AbstractMethod to delete blog by id
     */
    BlogV2 deleteBlog(int id);

    /**
     * AbstractMethod to update a blog
     */
    BlogV2 updateBlog(BlogV2 blogV2);
}
