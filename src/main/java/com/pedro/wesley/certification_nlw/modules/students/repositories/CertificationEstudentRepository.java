package com.pedro.wesley.certification_nlw.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.wesley.certification_nlw.modules.students.entities.CertificationStudentEntity;

@Repository
public interface CertificationEstudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.tecnology = :tecnology")
    List<CertificationStudentEntity> findByStudentEmailAndTecnology(String email, String tecnology);
}
