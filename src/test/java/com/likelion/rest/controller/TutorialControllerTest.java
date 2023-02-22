package com.likelion.rest.controller;
import com.likelion.rest.entity.Tutorial;
import com.likelion.rest.service.TutorialService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TutorialController.class)
class TutorialControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TutorialService tutorialService;
    @Test
    @DisplayName("Test GetAllTutorials API")
    void getAllTutorials() throws Exception{
        // chuẩn bị dữ liệu test
        List<Tutorial> expectedTutorials = Arrays.asList(
                new Tutorial(1L, "Spring Boot Tutorial", "Learn Spring Boot", true),
                new Tutorial(2L, "Introduction to Spring Boot", "Get started with Spring Boot", true)
        );
        when(tutorialService.findAll()).thenReturn(expectedTutorials);

        // gọi phương thức test và kiểm tra kết quả
        mockMvc.perform(get("/api/tutorials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andReturn();
        verify(tutorialService, times(1)).findAll();
    }

    @Test
    @DisplayName("Test GetTutorialById API")
    void getTutorialById() throws Exception {
        // chuẩn bị dữ liệu test
        long tutorialId = 1628L;
        Tutorial expectedTutorial = new Tutorial(tutorialId, "Spring Boot Tutorial", "Learn Spring Boot", true);
        when(tutorialService.findById(tutorialId)).thenReturn(expectedTutorial);

        // gọi phương thức test và kiểm tra kết quả
        mockMvc.perform(get("/api/tutorials/1628"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1628)))
                .andReturn();
        verify(tutorialService, times(1)).findById(1628L);
    }

    @Test
    void createTutorial() throws Exception {
        Tutorial tutorial = new Tutorial(1L,"Spring Boot Tutorial", "Learn how to build a Spring Boot app", true);
        when(tutorialService.saveTutorial(any())).thenReturn(tutorial);

        // When
        mockMvc.perform(post("/api/tutorials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Spring Boot Tutorial\", \"description\": \"Learn how to build a Spring Boot app\", \"published\": true}"))

                // Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Spring Boot Tutorial")))
                .andExpect(jsonPath("$.description", is("Learn how to build a Spring Boot app")))
                .andExpect(jsonPath("$.published", is(true)));
    }


    @Test
    @DisplayName("Test DeleteTutorial API")
    void deleteTutorial() throws Exception {
        Long tutorialId = 1L;
        doNothing().when(tutorialService).deleteById(tutorialId);
        mockMvc.perform(delete("/api/tutorials/{id}", tutorialId))
                .andExpect(status().isNoContent());
        verify(tutorialService,times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test DeleteAllTutorials API")
    void deleteAllTutorials() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tutorials"))
                .andExpect(status().isNoContent());

        verify(tutorialService, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Test FindByPublished API")
    void findByPublished() throws Exception {
        // chuẩn bị dữ liệu test
        List<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial(1L,"Tutorial 1", "Description 1", true));
        tutorials.add(new Tutorial(2L,"Tutorial 2", "Description 2", false));
        tutorials.add(new Tutorial(3L,"Tutorial 3", "Description 3", true));
        when(tutorialService.findByPublished(true)).thenReturn(tutorials.stream()
                .filter(Tutorial::getPublished)
                .collect(Collectors.toList()));
        when(tutorialService.findByPublished(false)).thenReturn(tutorials.stream()
                .filter(t -> !t.getPublished())
                .collect(Collectors.toList()));

        // When
        mockMvc.perform(get("/api/tutorials/published"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Tutorial 1")))
                .andExpect(jsonPath("$[1].title", is("Tutorial 3")));

        verify(tutorialService, times(1)).findByPublished(true);
        verifyNoMoreInteractions(tutorialService);
    }
}