package com.mkm.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    protected Semester() {}

    public Semester(String name, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.id = "SEM- " + UUID.randomUUID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }
}
