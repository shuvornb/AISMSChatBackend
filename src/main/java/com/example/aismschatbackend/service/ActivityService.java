package com.example.aismschatbackend.service;

import com.example.aismschatbackend.communication.request.SaveActivityRequest;
import com.example.aismschatbackend.communication.response.GetActivityListResponse;
import com.example.aismschatbackend.db.entity.AISMSActivity;
import com.example.aismschatbackend.db.entity.AISMSActivityResponse;
import com.example.aismschatbackend.db.repository.AISMSActivityRepository;
import com.example.aismschatbackend.db.repository.AISMSActivityResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private AISMSActivityRepository activityRepository;

    @Autowired
    private AISMSActivityResponseRepository activityResponseRepository;

    public GetActivityListResponse getActivityList() {
        GetActivityListResponse response = new GetActivityListResponse();

        List<AISMSActivity> activityList = activityRepository.findAll();
        response.activityCount = activityList.size();
        response.activityList = activityList;

        return response;
    }

    public void saveActivityResponse(SaveActivityRequest request) {
        AISMSActivityResponse response = new AISMSActivityResponse();
        response.participantId = request.participantId;
        response.activityId = request.activityId;
        response.responseTime = request.responseTime;
        response.response = request.response;

        activityResponseRepository.save(response);
    }
}
