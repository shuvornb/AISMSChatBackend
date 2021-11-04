package com.example.aismschatbackend.db.repository;

import com.example.aismschatbackend.db.entity.AISMSPrimaryAssessmentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AISMSPrimaryAssessmentLogRepository extends JpaRepository<AISMSPrimaryAssessmentLog, Long> {
    List<AISMSPrimaryAssessmentLog> findByParticipantId(Long participantId);
}
