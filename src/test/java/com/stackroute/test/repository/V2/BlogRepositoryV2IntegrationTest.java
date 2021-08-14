package com.stackroute.test.repository.V2;

import com.stackroute.domain.V2.BlogAuthor;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.repository.V2.BlogRepositoryV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BlogRepositoryV2IntegrationTest {

    @Autowired
    private BlogRepositoryV2 blogRepositoryV2;
    private BlogV2 blogV2;
    private BlogAuthor blogAuthor;

    @BeforeEach
    public void setUp() {
        blogV2 = new BlogV2();
        blogAuthor = new BlogAuthor();
        blogAuthor.setFirstName("Imneet");
        blogAuthor.setLastName("Kaur");
        blogV2.setBlogId(1);
        blogV2.setBlogTitle("Blog1");
        blogV2.setBlogAuthor(blogAuthor);
        blogV2.setBlogContent("Sample content");
    }

    @AfterEach
    public void tearDown() {
        blogRepositoryV2.deleteAll();
        blogV2 = null;
    }

    @Test
    public void givenBlogToSaveThenShouldReturnSavedBlog() {
        blogRepositoryV2.save(blogV2);
        BlogV2 fetchedBlog = blogRepositoryV2.findById(blogV2.getBlogId()).get();
        assertEquals(1, fetchedBlog.getBlogId());
    }


    @Test
    public void givenGetAllBlogsThenShouldReturnListOfAllBlogs() {
        BlogV2 blog = new BlogV2(2, "Demo2", blogAuthor, "Sample2");
        BlogV2 blog1 = new BlogV2(3, "Demo3", blogAuthor, "Sample3");
        blogRepositoryV2.save(blog);
        blogRepositoryV2.save(blog1);

        List<BlogV2> blogList = (List<BlogV2>) blogRepositoryV2.findAll();
        assertEquals("Demo3", blogList.get(1).getBlogTitle());
    }

    @Test
    public void givenBlogIdThenShouldReturnRespectiveBlog() {
        BlogV2 blog = new BlogV2(9, "Demo9", blogAuthor, "SampleBlog");
        BlogV2 blog1 = blogRepositoryV2.save(blog);
        Optional<BlogV2> optional = blogRepositoryV2.findById(blog1.getBlogId());
        assertEquals(blog1.getBlogId(), optional.get().getBlogId());
        assertEquals(blog1.getBlogTitle(), optional.get().getBlogTitle());
        assertEquals(blog1.getBlogContent(), optional.get().getBlogContent());
    }

    @Test
    public void givenBlogIdToDeleteThenShouldReturnDeletedBlog() {
        BlogV2 blog = new BlogV2(4, "Demo4", blogAuthor, "Sample4");
        blogRepositoryV2.save(blog);
        blogRepositoryV2.deleteById(blog.getBlogId());
        Optional optional = blogRepositoryV2.findById(blog.getBlogId());
        assertEquals(Optional.empty(), optional);
    }

}