package com.stackroute.domain.V2;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

/**
 * Add annotation to declare this class as JPA Entity
 */
@Entity
public class BlogV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blogId;
    private String blogTitle;
    /**
     * Add Annotation to specify one to one mapping
     */
    @OneToOne
    private BlogAuthor blogAuthor;
    private String blogContent;

    /**
     * default constructor
     */
    public BlogV2() {
    }

    /**
     * parametrized constructor
     */
    public BlogV2(int blogId, String blogTitle, BlogAuthor blogAuthor, String blogContent) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogAuthor = blogAuthor;
        this.blogContent = blogContent;
    }
    /**
     * getters and setters
     */
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public BlogAuthor getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(BlogAuthor blogAuthor) {
        this.blogAuthor = blogAuthor;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}