package com.example.aismschatbackend.db.repository;

import com.example.aismschatbackend.db.entity.AISMSSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AISMSSessionRepository extends JpaRepository<AISMSSession, Long> {
    AISMSSession findTopById(Long sessionId);
}
