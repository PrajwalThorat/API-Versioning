package com.stackroute.test.controller.V2;

import com.stackroute.domain.V2.BlogAuthor;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.service.V2.BlogServiceV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BlogControllerV2IntegrationTest {

    @Autowired
    private BlogServiceV2 blogService;
    private BlogV2 blogV2;
    private BlogAuthor blogAuthor;
    private List<BlogV2> blogList;


    @BeforeEach
    public void setUp() {
        blogV2 = new BlogV2();
        blogAuthor = new BlogAuthor();
        blogAuthor.setFirstName("Imneet");
        blogAuthor.setLastName("Kaur");
        blogV2.setBlogId(1);
        blogV2.setBlogTitle("DemoBlog");
        blogV2.setBlogAuthor(blogAuthor);
        blogV2.setBlogContent("SampleBlogforTesting");
        blogList = new ArrayList<>();
        blogList.add(blogV2);
    }

    @AfterEach
    public void tearDown() {
        blogV2 = null;
    }

    @Test
    void givenBlogToSaveThenShouldReturnTheSavedBlog() throws Exception {
        BlogV2 savedBlog = blogService.saveBlog(blogV2);
        assertNotNull(savedBlog);
        assertEquals(blogV2.getBlogId(), savedBlog.getBlogId());
    }

    @Test
    public void givenGetAllBlogsThenBlogListShouldNotBeNull() throws Exception {
        List<BlogV2> blogList = blogService.getAllBlogs();
        assertNotNull(blogList);
    }

    @Test
    public void givenBlogToUpdateThenShouldReturnUpdatedBlog() throws Exception {
        blogV2.setBlogContent("Updated Blog content");
        blogService.updateBlog(blogV2);
        assertEquals("Updated Blog content", blogV2.getBlogContent());
    }
}


