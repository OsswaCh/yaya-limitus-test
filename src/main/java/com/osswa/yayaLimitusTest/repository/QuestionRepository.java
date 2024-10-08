package com.osswa.yayaLimitusTest.repository;

import com.osswa.yayaLimitusTest.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findBySurveyId(UUID surveyId);

}
