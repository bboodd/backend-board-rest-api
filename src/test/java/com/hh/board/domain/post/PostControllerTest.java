package com.hh.board.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(postController)
                .build();
    }

    @Test
    void getPosts() {
    }

    @DisplayName("게시글 단건 조회")
    @Test
    void getPost() throws Exception {
        //given
        int postId = 1;

        //when
        mockMvc.perform(get("/api-board/posts/{postId}", postId))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        then(postService).should().findPostById(1);
    }

    @Test
    void savePost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}