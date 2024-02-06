package com.pedro.wesley.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerififyHasCertificationDTO {
    private String email;
    private String tecnology;
}
