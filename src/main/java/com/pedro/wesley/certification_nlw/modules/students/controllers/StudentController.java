package com.pedro.wesley.certification_nlw.modules.students.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pedro.wesley.certification_nlw.modules.students.dto.StudentCertificationAnswersDTO;
import com.pedro.wesley.certification_nlw.modules.students.dto.VerififyHasCertificationDTO;
import com.pedro.wesley.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.pedro.wesley.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.pedro.wesley.certification_nlw.modules.students.useCases.VerifyHasCertificationUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyHasCertificationUseCase verifyHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("verififyIfHasCertification")
    public String verififyIfHasCertification(@RequestBody VerififyHasCertificationDTO verififyHasCertificationDTO) {
        var result = verifyHasCertificationUseCase.execute(verififyHasCertificationDTO);
        if (result) {
            return "Usuário possui a certificação";
        }
        return "Usuário não possui a certificação";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswersDTO dto) {
        try {
            CertificationStudentEntity result = studentCertificationAnswersUseCase.execute(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
