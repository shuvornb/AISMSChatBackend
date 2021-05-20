package com.example.aismschatbackend.db.repository;

import com.example.aismschatbackend.db.entity.AISMSActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AISMSActivityRepository extends JpaRepository<AISMSActivity, Long> {
    //List<AISMSActivity> findAll();
}
