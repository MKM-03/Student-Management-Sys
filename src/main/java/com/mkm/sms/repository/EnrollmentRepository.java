package com.mkm.sms.repository;

import com.mkm.sms.service.SemesterRecord;

import java.util.List;

public interface EnrollmentRepository {

    void save(SemesterRecord record);

    List<SemesterRecord> findByStudentId(String stdId);

    List<SemesterRecord> findBySemesterId(String semId);

    SemesterRecord findByStudentIdAndSemesterId(String stdId, String semId);

    void deleteByStudentIdAndSemsterId(String stdId, String semId);
}
