package com.likelion.rest.service.impl;

import com.likelion.rest.entity.Tutorial;
import com.likelion.rest.repository.TutorialRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class TutorialServiceImplTest {
    @Mock
    private TutorialRepository tutorialRepository;
    @InjectMocks
    private TutorialServiceImpl tutorialService;
    @BeforeEach
    void setUp(){

    }
    @AfterEach
    void tearDown(){

    }
    @Test
    @DisplayName("Test FindAll Service")
    void findAll() {
        tutorialRepository.findAll();
        verify(tutorialRepository).findAll();
    }

    @Test
    @DisplayName("Test FindByTitleContaining Service")
    void findByTitleContaining() {
        // chuẩn bị dữ liệu test
        String keyword = "Spring Boot";
        List<Tutorial> expectedTutorials = Arrays.asList(
                new Tutorial(1L, "Spring Boot Tutorial", "Learn Spring Boot", true),
                new Tutorial(2L, "Introduction to Spring Boot", "Get started with Spring Boot", true)
        );
        when(tutorialRepository.findByTitleContaining(keyword)).thenReturn(expectedTutorials);

        // gọi phương thức test và kiểm tra kết quả
        List<Tutorial> actualTutorials = tutorialService.findByTitleContaining(keyword);
        assertEquals(expectedTutorials, actualTutorials);
        verify(tutorialRepository, times(1)).findByTitleContaining(keyword);
    }

    @Test
    @DisplayName("Test FindById Service")
    void findById() {
        //chuẩn bị dữ liệu test
        Tutorial tutorial = new Tutorial(20L,"SpringBoot Tutorial", "Learn SpringBoot Test", true);
        //giả lập phương thức của repository
        when(tutorialRepository.findById(20L)).thenReturn(Optional.of(tutorial));
        //gọi phương thức test và kiểm tra kết quả
        Tutorial foundTutorial = tutorialService.findById(20L);
        assertEquals(tutorial, foundTutorial);
        verify(tutorialRepository,times(1)).findById(20L);
    }

    @Test
    @DisplayName("Test DeleteById Service")
    void deleteById() {
        //giả lập phương thức của repository
        doNothing().when(tutorialRepository).deleteById(20L);
        //gọi phương thức test và kiểm tra kết quả
        tutorialService.deleteById(20L);
        verify(tutorialRepository,times(1)).deleteById(20L);
    }

    @Test
    @DisplayName("Test DeleteAll Service")
    void deleteAll() {
        //giả lập phương thức của repository
        doNothing().when(tutorialRepository).deleteAll();
        //gọi phương thức test và kiểm tra kết quả
        tutorialService.deleteAll();
        verify(tutorialRepository, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Test FindByPublished Service")
    void findByPublished() {
        List<Tutorial> expectedTutorials = Arrays.asList(
                new Tutorial(1L, "Spring Boot Tutorial", "Learn Spring Boot", true),
                new Tutorial(2L, "Introduction to Spring Boot", "Get started with Spring Boot", true)
        );
        when(tutorialRepository.findByPublished(true)).thenReturn(expectedTutorials);
        // gọi phương thức test và kiểm tra kết quả
        List<Tutorial> actualTutorials = tutorialService.findByPublished(true);
        assertEquals(expectedTutorials, actualTutorials);
        verify(tutorialRepository, times(1)).findByPublished(true);


    }

    @Test
    @DisplayName("Test SaveTutorial Service")
    void saveTutorial() {
        // chuẩn bị dữ liệu test
        Tutorial tutorialToSave = new Tutorial(8L,"Spring Boot Test Tutorial", "Learn Spring Boot Test", true);
        Tutorial expectedTutorial = new Tutorial(8L, "Spring Boot Test Tutorial", "Learn Spring Boot Test", true);
        when(tutorialRepository.save(tutorialToSave)).thenReturn(expectedTutorial);

        // gọi phương thức test và kiểm tra kết quả
        Tutorial actualTutorial = tutorialService.saveTutorial(tutorialToSave);
        assertEquals(expectedTutorial, actualTutorial);
        verify(tutorialRepository, times(1)).save(tutorialToSave);
    }
}