package com.osswa.yayaLimitusTest.repository;

import com.osswa.yayaLimitusTest.Model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    List<Survey>  findByIsActiveTrue();
}
