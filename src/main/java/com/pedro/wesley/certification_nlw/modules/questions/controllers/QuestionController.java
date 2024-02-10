package com.pedro.wesley.certification_nlw.modules.questions.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pedro.wesley.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.pedro.wesley.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.pedro.wesley.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.pedro.wesley.certification_nlw.modules.questions.entities.QuestionEntity;
import com.pedro.wesley.certification_nlw.modules.questions.repositories.QuestionRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("technology/{technology}")
    public List<QuestionResultDTO> listFindByTechnology(@PathVariable String technology) {
        List<QuestionEntity> result = questionRepository.findByTechnology(technology);

        List<QuestionResultDTO> toMap = result.stream()
                .map(QuestionController::mapQuestionToDTO)
                .collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity questions) {
        QuestionResultDTO questionResultDTO = QuestionResultDTO.builder()
                .description(questions.getDescription())
                .technology(questions.getTechnology())
                .id(questions.getId())
                .build();

        List<AlternativesResultDTO> alternatives = questions.getAlternatives().stream()
                .map(QuestionController::mapAlternativeToDTO)
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternatives);

        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternative) {
        return AlternativesResultDTO.builder()
                .description(alternative.getDescription())
                .id(alternative.getId())
                .build();
    }
}