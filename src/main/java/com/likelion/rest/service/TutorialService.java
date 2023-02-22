package com.likelion.rest.service;

import com.likelion.rest.entity.Tutorial;

import java.util.List;

public interface TutorialService {
    List<Tutorial>findAll();
    List<Tutorial>findByTitleContaining(String title);
    Tutorial findById(Long id);
    void deleteById(Long id);
    void deleteAll();
    List<Tutorial>findByPublished(boolean b);
    Tutorial saveTutorial(Tutorial tutorial);
}
