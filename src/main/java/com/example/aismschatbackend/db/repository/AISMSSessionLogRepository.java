package com.example.aismschatbackend.db.repository;

import com.example.aismschatbackend.db.entity.AISMSSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AISMSSessionLogRepository extends JpaRepository<AISMSSessionLog, Long> {
    List<AISMSSessionLog> findByParticipantId(Long participantId);
}
