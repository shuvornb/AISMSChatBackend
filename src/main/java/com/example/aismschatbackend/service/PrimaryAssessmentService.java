package com.example.aismschatbackend.service;

import com.example.aismschatbackend.communication.request.AddPrimaryAssessmentRequest;
import com.example.aismschatbackend.communication.request.FiveKeyAssessRequest;
import com.example.aismschatbackend.communication.response.GetPrimaryAssessmentListResponse;
import com.example.aismschatbackend.db.entity.AISMSPrimaryAssessmentLog;
import com.example.aismschatbackend.db.entity.AISMSRWAT;
import com.example.aismschatbackend.db.repository.AISMSPrimaryAssessmentLogRepository;
import com.example.aismschatbackend.db.repository.AISMSRWATRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimaryAssessmentService {

    @Autowired
    private AISMSPrimaryAssessmentLogRepository primaryAssessmentLogRepository;

    @Autowired
    private AISMSRWATRepository rwatRepository;

    public void addPrimaryAssessment(AddPrimaryAssessmentRequest request) {
        AISMSPrimaryAssessmentLog assessmentLog = new AISMSPrimaryAssessmentLog();
        assessmentLog.participantId = request.participantId;
        assessmentLog.assessmentType = request.assessmentType;
        assessmentLog.status = "PENDING";

        // TODO call scheduler service to create a meeting with date, startTime, duration and fetch meetingId, and other meeting related information

        assessmentLog.meetingId = 1111111L;
        assessmentLog.meetingURL = "https://fsu.zoom.us/j/94855873091";
        assessmentLog.meetingStartTime = 12345678L;
        assessmentLog.meetingDuration = "1 hr";
        primaryAssessmentLogRepository.save(assessmentLog);
    }

    public GetPrimaryAssessmentListResponse getPrimaryAssessmentList(Long participantId) {
        GetPrimaryAssessmentListResponse response = new GetPrimaryAssessmentListResponse();
        List<AISMSPrimaryAssessmentLog> primaryAssessmentLogList = primaryAssessmentLogRepository.findByParticipantId(participantId);
        response.assessmentCount = primaryAssessmentLogList.size();
        response.primaryAssessmentLogList = primaryAssessmentLogList;
        return response;
    }

    public void updateRWATResult(Long userId, FiveKeyAssessRequest fiveKey) {
        AISMSRWAT rwatResult = new AISMSRWAT(fiveKey, userId);
        rwatRepository.save(rwatResult);
    }
}
