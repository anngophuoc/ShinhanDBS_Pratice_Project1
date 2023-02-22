package com.likelion.rest.repository;

import com.likelion.rest.entity.Tutorial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TutorialRepositoryTest {
    @Autowired
    private TutorialRepository tutorialRepository;
    @AfterEach
    void tearDown(){
        tutorialRepository.deleteAll();
    }
    @Test
    @DisplayName("Test FindByPublished")
    void findByPublished() {
        List<Tutorial> publishedTutorials = tutorialRepository.findByPublished(true);
        assertThat(publishedTutorials).isNotNull();
        assertTrue(publishedTutorials.stream().allMatch(Tutorial::getPublished));
    }

    @Test
    @DisplayName("Test FindByTitleContaining")
    void findByTitleContaining() {
        List<Tutorial> searchTutorials = tutorialRepository.findByTitleContaining("Tutorial1");
        assertThat(searchTutorials).isNotNull();
        assertTrue(searchTutorials.stream().allMatch(tutorial -> tutorial.getTitle().contains("Tutorial1")));
    }
}