package com.mkm.sms.repository;

import com.mkm.sms.entity.SemesterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<SemesterRecord, String> {

    List<SemesterRecord> findByStudentStudentID(String stdId);

    List<SemesterRecord> findBySemesterId(String semId);

    SemesterRecord findByStudentStudentIDAndSemesterId(String stdId, String semId);
}
