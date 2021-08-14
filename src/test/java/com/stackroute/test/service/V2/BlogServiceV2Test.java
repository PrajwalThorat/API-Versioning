package com.stackroute.test.service.V2;

import com.stackroute.domain.V2.BlogAuthor;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.repository.V2.BlogRepositoryV2;
import com.stackroute.service.V2.BlogServiceImplV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceV2Test {
    @Mock
    private BlogRepositoryV2 blogRepositoryV2;

    @InjectMocks
    private BlogServiceImplV2 blogServiceImplV2;
    private BlogAuthor blogAuthor;
    private BlogV2 blogV2, blogV21;
    private List<BlogV2> blogList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        blogAuthor = new BlogAuthor("Imneet", "Kaur");
        blogV2 = new BlogV2(1, "SampleBlog", blogAuthor, "SampleBlogforTesting");
        blogV21 = new BlogV2(2, "Blog 1", blogAuthor, "Sample Blog 1 for Testing");
        optional = Optional.of(blogV2);
    }

    @AfterEach
    public void tearDown() {
        blogV2 = null;
    }

    @Test
    public void givenBlogToSaveThenShouldReturnSavedBlog() {
        when(blogRepositoryV2.save(any())).thenReturn(blogV2);
        assertEquals(blogV2, blogServiceImplV2.saveBlog(blogV2));
        verify(blogRepositoryV2, times(1)).save(any());
    }

    @Test
    public void givenBlogToSaveThenShouldNotReturnSavedBlog() {
        when(blogRepositoryV2.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            blogServiceImplV2.saveBlog(blogV2);
        });
        verify(blogRepositoryV2, times(1)).save(any());
    }

    @Test
    public void givenGetAllBlogsThenShouldReturnListOfAllBlogs() {
        blogRepositoryV2.save(blogV2);
        //stubbing the mock to return specific data
        when(blogRepositoryV2.findAll()).thenReturn(blogList);
        List<BlogV2> blogList1 = blogServiceImplV2.getAllBlogs();
        assertEquals(blogList, blogList1);
        verify(blogRepositoryV2, times(1)).save(blogV2);
        verify(blogRepositoryV2, times(1)).findAll();
    }

    @Test
    public void givenBlogIdThenShouldReturnRespectiveBlog() {
        when(blogRepositoryV2.findById(anyInt())).thenReturn(Optional.of(blogV2));
        BlogV2 retrievedBlog = blogServiceImplV2.getBlogById(blogV2.getBlogId());
        assertEquals(1,retrievedBlog.getBlogId());
        verify(blogRepositoryV2, times(1)).findById(anyInt());

    }

    @Test
    void givenBlogIdToDeleteThenShouldReturnDeletedBlog() {
        when(blogRepositoryV2.findById(blogV2.getBlogId())).thenReturn(optional);
        BlogV2 deletedBlog = blogServiceImplV2.deleteBlog(1);
        assertEquals(1, deletedBlog.getBlogId());
        verify(blogRepositoryV2, times(2)).findById(blogV2.getBlogId());
        verify(blogRepositoryV2, times(1)).deleteById(blogV2.getBlogId());
    }

    @Test
    void givenBlogIdToDeleteThenShouldNotReturnDeletedBlog() {
        when(blogRepositoryV2.findById(blogV2.getBlogId())).thenReturn(Optional.empty());
        BlogV2 deletedBlog = blogServiceImplV2.deleteBlog(1);
        verify(blogRepositoryV2, times(1)).findById(blogV2.getBlogId());
    }

    @Test
    public void givenBlogToUpdateThenShouldReturnUpdatedBlog() {
        when(blogRepositoryV2.findById(blogV2.getBlogId())).thenReturn(optional);
        when(blogRepositoryV2.save(blogV2)).thenReturn(blogV2);
        blogV2.setBlogContent("SampleBlogforTesting");
        BlogV2 blog1 = blogServiceImplV2.updateBlog(blogV2);
        assertEquals(blog1.getBlogContent(), "SampleBlogforTesting");
        verify(blogRepositoryV2, times(1)).save(blogV2);
        verify(blogRepositoryV2, times(2)).findById(blogV2.getBlogId());
    }

    @Test
    public void givenBlogToUpdateThenShouldNotReturnUpdatedBlog() {
        when(blogRepositoryV2.findById(blogV2.getBlogId())).thenReturn(Optional.empty());
        BlogV2 blog1 = blogServiceImplV2.updateBlog(blogV2);
        assertNull(blog1);
        verify(blogRepositoryV2, times(1)).findById(blogV2.getBlogId());
    }

}