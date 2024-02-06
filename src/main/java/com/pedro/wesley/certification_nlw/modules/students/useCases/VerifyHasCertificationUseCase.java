package com.pedro.wesley.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.pedro.wesley.certification_nlw.modules.students.dto.VerififyHasCertificationDTO;

@Service
public class VerifyHasCertificationUseCase {

    public boolean execute(VerififyHasCertificationDTO verififyHasCertificationDTO) {
        if (verififyHasCertificationDTO.getEmail().equals("pedrodev101@gmail.com")
                && verififyHasCertificationDTO.getTecnology().equals("JAVA")) {
            return true;
        }
        return false;
    }
}
