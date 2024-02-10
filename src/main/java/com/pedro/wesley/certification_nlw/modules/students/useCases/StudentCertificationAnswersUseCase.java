package com.pedro.wesley.certification_nlw.modules.students.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.wesley.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.pedro.wesley.certification_nlw.modules.questions.entities.QuestionEntity;
import com.pedro.wesley.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.pedro.wesley.certification_nlw.modules.students.dto.StudentCertificationAnswersDTO;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    QuestionRepository questionRepository;

    public StudentCertificationAnswersDTO execute(StudentCertificationAnswersDTO dto) {

        List<QuestionEntity> questionEntity = questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers().forEach(questionAnswer -> {
            Optional<QuestionEntity> optionalQuestion = questionEntity.stream()
                    .filter(q -> q.getId().equals(questionAnswer.getQuestionId()))
                    .findFirst();

            if (optionalQuestion.isPresent()) {
                QuestionEntity question = optionalQuestion.get();
                AlternativesEntity correctAlternative = question.getAlternatives().stream()
                        .filter(AlternativesEntity::isCorrect)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Correct alternative not found"));

                if (correctAlternative.getId().equals(questionAnswer.getAlternativeId())) {
                    questionAnswer.setCorrect(true);
                } else {
                    questionAnswer.setCorrect(false);
                }
            } else {
                // Handle the case where the question is not found
                // This could involve logging an error, skipping this iteration, etc.
            }
        });

        return dto;
    }
}
