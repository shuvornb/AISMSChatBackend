package com.example.aismschatbackend.communication.response;

import com.example.aismschatbackend.db.entity.AISMSActivity;

import java.util.List;

public class GetActivityListResponse extends BasicRestResponse{
    public Integer activityCount;
    public List<AISMSActivity> activityList;

    public GetActivityListResponse() {

    }

    public GetActivityListResponse(String message, Integer activityCount, List<AISMSActivity> activityList) {
        super(message);
        this.activityCount = activityCount;
        this.activityList = activityList;
    }

    public GetActivityListResponse(Integer activityCount, List<AISMSActivity> activityList) {
        this.activityCount = activityCount;
        this.activityList = activityList;
    }

    @Override
    public String toString() {
        return "GetActivityListResponse{" +
                "activityCount=" + activityCount +
                ", activityList=" + activityList +
                ", message='" + message + '\'' +
                '}';
    }
}
