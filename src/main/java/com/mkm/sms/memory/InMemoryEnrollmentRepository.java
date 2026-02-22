package com.mkm.sms.memory;

import com.mkm.sms.repository.EnrollmentRepository;
import com.mkm.sms.service.SemesterRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEnrollmentRepository implements EnrollmentRepository {

    private final Map<String, SemesterRecord> records = new HashMap<>();


    private String key(String studentId, String semesterId) {
        return studentId + "::" + semesterId;
    }

    @Override
    public void save(SemesterRecord record) {
        String k = key(record.getStudent().getStudentID(), record.getSemester().id());
        records.put(k, record);
    }

    @Override
    public List<SemesterRecord> findByStudentId(String stdId) {
        List<SemesterRecord> result = new ArrayList<>();

        for (SemesterRecord r : records.values()) {
            if (r.getStudent().getStudentID().equals(stdId)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<SemesterRecord> findBySemesterId(String semId) {
        List<SemesterRecord> result = new ArrayList<>();

        for (SemesterRecord r : records.values()) {
            if (r.getSemester().id().equals(semId)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public SemesterRecord findByStudentIdAndSemesterId(String stdId, String semId) {
        return records.get(key(stdId, semId));
    }

    @Override
    public void deleteByStudentIdAndSemsterId(String stdId, String semId) {
        records.remove(key(stdId, semId));
    }
}
