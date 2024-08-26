package com.osswa.yayaLimitusTest.repository;

import com.osswa.yayaLimitusTest.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findBySurveyResponseId (UUID surveyResponseId);
}
