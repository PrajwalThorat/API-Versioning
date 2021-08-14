package com.stackroute.service.V2;

import com.stackroute.domain.V2.BlogV2;
import com.stackroute.repository.V2.BlogRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class should implement BlogServiceV2 Interface and override all the methods and provide implementation for all the methods
 */
@Service
public class BlogServiceImplV2 implements BlogServiceV2{

    private BlogRepositoryV2 blogRepositoryV2;

    @Autowired
    public BlogServiceImplV2(BlogRepositoryV2 blogRepositoryV2){
        this.blogRepositoryV2 = blogRepositoryV2;
    }

    @Override
    public BlogV2 saveBlog(BlogV2 blogV2) {
        return blogRepositoryV2.save(blogV2);
    }

    @Override
    public List<BlogV2> getAllBlogs() {
        return (List<BlogV2>) blogRepositoryV2.findAll();
    }

    @Override
    public BlogV2 getBlogById(int id) {
        BlogV2 blogV2 = null;
        blogV2 = blogRepositoryV2.findById(id).get();
        return blogV2;
    }

    @Override
    public BlogV2 deleteBlog(int id) {
        BlogV2 blogV2 = null;
        Optional optional = blogRepositoryV2.findById(id);
        if (optional.isPresent()){
            blogV2 = blogRepositoryV2.findById(id).get();
            blogRepositoryV2.deleteById(id);
        }
        return blogV2;
    }

    @Override
    public BlogV2 updateBlog(BlogV2 blogV2) {
        BlogV2 updateBlog = null;
        Optional optional = blogRepositoryV2.findById(blogV2.getBlogId());
        if (optional.isPresent()){
            BlogV2 getBlog = blogRepositoryV2.findById(blogV2.getBlogId()).get();
            getBlog.setBlogContent(blogV2.getBlogContent());
            updateBlog = saveBlog(getBlog);
        }
        return updateBlog;
    }
}
