package com.mkm.sms.repository;

import com.mkm.sms.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, String> {}
