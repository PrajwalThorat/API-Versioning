package com.stackroute.test.controller.V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.V2.BlogControllerV2;
import com.stackroute.domain.V2.BlogAuthor;
import com.stackroute.domain.V2.BlogV2;
import com.stackroute.service.V2.BlogServiceV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BlogControllerV2Test {

    private MockMvc mockMvc;
    @Mock
    BlogServiceV2 blogServiceV2;
    @InjectMocks
    private BlogControllerV2 blogControllerV2;

    private BlogV2 blogV2;
    private BlogAuthor blogAuthor;
    private List<BlogV2> blogList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogControllerV2).build();
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
    public void givenBlogToSaveThenShouldReturnSavedBlog() throws Exception {
        when(blogServiceV2.saveBlog(any())).thenReturn(blogV2);
        mockMvc.perform(post("/api/v2/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(blogV2)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(blogServiceV2, times(1)).saveBlog(any());

    }

    @Test
    public void givenGetAllBlogsThenShouldReturnListOfAllBlogs() throws Exception {
        when(blogServiceV2.getAllBlogs()).thenReturn(blogList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/blogs")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(blogV2)))
                .andDo(MockMvcResultHandlers.print());
        verify(blogServiceV2).getAllBlogs();
        verify(blogServiceV2, times(1)).getAllBlogs();

    }

    @Test
    void givenBlogIdThenShouldReturnRespectiveBlog() throws Exception {
        when(blogServiceV2.getBlogById(blogV2.getBlogId())).thenReturn(blogV2);
        mockMvc.perform(get("/api/v2/blog/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenBlogIdToDeleteThenShouldNotReturnDeletedBlog() throws Exception {
        when(blogServiceV2.deleteBlog(blogV2.getBlogId())).thenReturn(blogV2);
        mockMvc.perform(delete("/api/v2/blog/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(blogV2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenBlogToUpdateThenShouldReturnUpdatedBlog() throws Exception {
        when(blogServiceV2.updateBlog(any())).thenReturn(blogV2);
        mockMvc.perform(put("/api/v2/blog").contentType(MediaType.APPLICATION_JSON).content(asJsonString(blogV2)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
