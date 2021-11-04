package com.example.aismschatbackend.service;

import com.example.aismschatbackend.communication.request.AddSessionRequest;
import com.example.aismschatbackend.communication.response.GetSessionListResponse;
import com.example.aismschatbackend.communication.response.SingleSessionResponse;
import com.example.aismschatbackend.db.entity.AISMSSession;
import com.example.aismschatbackend.db.entity.AISMSSessionLog;
import com.example.aismschatbackend.db.repository.AISMSSessionLogRepository;
import com.example.aismschatbackend.db.repository.AISMSSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private AISMSSessionLogRepository sessionLogRepository;

    @Autowired
    private AISMSSessionRepository sessionRepository;

    public void addSession(AddSessionRequest request) {
        AISMSSessionLog sessionLog = new AISMSSessionLog();
        sessionLog.participantId = request.participantId;
        sessionLog.sessionId = request.sessionId;
        sessionLog.sessionType = request.sessionType;
        sessionLog.status = "PENDING";

        if(request.sessionType.equals("ZOOM")) {
            // TODO call scheduler service to create a meeting with date, startTime, duration and fetch meetingId, and other meeting related information

            sessionLog.meetingId = 1111111L;
            sessionLog.meetingURL = "https://fsu.zoom.us/j/94855873091";
            sessionLog.meetingStartTime = 12345678L;
            sessionLog.meetingDuration = "1 hr";
        }
        sessionLogRepository.save(sessionLog);
    }

    public GetSessionListResponse getSessionList(Long participantId) {
        GetSessionListResponse response = new GetSessionListResponse();
        List<AISMSSessionLog> sessionLogList = sessionLogRepository.findByParticipantId(participantId);
        List<SingleSessionResponse> list = new ArrayList<>();
        for(AISMSSessionLog sessionLog : sessionLogList) {

            AISMSSession session = sessionRepository.findTopById(sessionLog.sessionId);
            SingleSessionResponse singleSessionResponse = new SingleSessionResponse();
            singleSessionResponse.sessionId = sessionLog.sessionId;
            singleSessionResponse.title = session.title;
            singleSessionResponse.description = session.description;
            singleSessionResponse.questions = session.questions;
            singleSessionResponse.associatedKey = session.associatedKey;
            singleSessionResponse.sessionType = sessionLog.sessionType;
            singleSessionResponse.status = sessionLog.status;
            singleSessionResponse.startTime = sessionLog.startTime;
            singleSessionResponse.endTime = sessionLog.endTime;
            singleSessionResponse.meetingId = sessionLog.meetingId;
            singleSessionResponse.meetingURL = sessionLog.meetingURL;
            singleSessionResponse.meetingStartTime = sessionLog.meetingStartTime;
            singleSessionResponse.meetingDuration = sessionLog.meetingDuration;

            list.add(singleSessionResponse);
        }

        response.sessionCount = sessionLogList.size();
        response.sessionLogList = list;
        return response;
    }
}
