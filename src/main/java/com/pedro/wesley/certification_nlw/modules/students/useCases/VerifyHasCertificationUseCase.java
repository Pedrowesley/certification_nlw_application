package com.pedro.wesley.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.wesley.certification_nlw.modules.students.dto.VerififyHasCertificationDTO;
import com.pedro.wesley.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationEstudentRepository;

    public boolean execute(VerififyHasCertificationDTO dto) {

        var result = certificationEstudentRepository.findByStudentEmailAndTecnology(dto.getEmail(), dto.getTecnology());

        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
