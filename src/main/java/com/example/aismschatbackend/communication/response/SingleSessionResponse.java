package com.example.aismschatbackend.communication.response;

import java.util.Map;

public class SingleSessionResponse extends BasicRestResponse{
    public Long sessionId;
    public String title;
    public String description;
    public Map<String, Object> questions;
    public String associatedKey;
    public String sessionType;
    public String status;
    public Long startTime;
    public Long endTime;
    public Long meetingId;
    public String meetingURL;
    public Long meetingStartTime;
    public String meetingDuration;

    public SingleSessionResponse() {

    }

    @Override
    public String toString() {
        return "SingleSessionResponse{" +
                "sessionId=" + sessionId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", associatedKey='" + associatedKey + '\'' +
                ", sessionType='" + sessionType + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingId=" + meetingId +
                ", meetingURL='" + meetingURL + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingDuration='" + meetingDuration + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
