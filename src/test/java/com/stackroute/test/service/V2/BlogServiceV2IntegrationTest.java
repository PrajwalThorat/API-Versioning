package com.stackroute.test.service.V2;

import com.stackroute.domain.V2.BlogAuthor;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.repository.V2.BlogRepositoryV2;
import com.stackroute.service.V2.BlogServiceImplV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BlogServiceV2IntegrationTest {
    @Autowired
    private BlogRepositoryV2 blogRepository;

    @Autowired
    private BlogServiceImplV2 blogService;
    private BlogV2 blogV2, blogV21;
    private List<BlogV2> blogList;
    private BlogAuthor blogAuthor;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        blogAuthor = new BlogAuthor("Imneet", "Kaur");
        blogV2 = new BlogV2(1, "SampleBlog", blogAuthor, "SampleBlogforTesting");
        blogV21 = new BlogV2(2, "Blog 1", blogAuthor, "Sample Blog 1 for Testing");
        blogList.add(blogV2);
        blogList.add(blogV21);
    }

    @AfterEach
    public void tearDown() {
        blogV2 = blogV21 = null;
        blogList = null;
    }

    @Test
    public void givenBlogToSaveThenShouldReturnSavedBlog() {
        BlogV2 savedBlog = blogRepository.save(blogV2);
        assertNotNull(savedBlog);
        assertEquals(blogV2.getBlogId(), savedBlog.getBlogId());
    }

    @Test
    public void givenGetAllBlogsThenShouldReturnListOfAllBlogs() {
        List<BlogV2> blogList = (List<BlogV2>) blogRepository.findAll();
        assertNotNull(blogList);
    }

}
