package com.pedro.wesley.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.wesley.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.pedro.wesley.certification_nlw.modules.questions.entities.QuestionEntity;
import com.pedro.wesley.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.pedro.wesley.certification_nlw.modules.students.dto.StudentCertificationAnswersDTO;
import com.pedro.wesley.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.pedro.wesley.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.pedro.wesley.certification_nlw.modules.students.entities.StudentEntity;
import com.pedro.wesley.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.pedro.wesley.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @SuppressWarnings("null")
    public CertificationStudentEntity execute(StudentCertificationAnswersDTO dto) {

        List<QuestionEntity> questionEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

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
                    correctAnswers.getAndIncrement();
                } else {
                    questionAnswer.setCorrect(false);
                }

                var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                        .answerId(questionAnswer.getAlternativeId())
                        .questionId(questionAnswer.getQuestionId())
                        .isCorrect(questionAnswer.isCorrect()).build();

                answersCertifications.add(answersCertificationsEntity);
            }
        });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        } else {
            studentId = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .tecnology(dto.getTechnology())
                .grade(correctAnswers.get())
                .studentId(studentId)
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.forEach(answer -> {
            answer.setCertificationId(certificationStudentEntity.getId());
            answer.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntity(answersCertifications);

        return certificationStudentCreated;
    }
}
