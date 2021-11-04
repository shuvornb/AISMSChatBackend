package com.example.aismschatbackend.communication.request;

import java.io.Serializable;

public class AddSessionRequest implements Serializable {
    public Long participantId;
    public Long sessionId;
    public String sessionType;
    public String date;
    public String startTime;
    public String duration;

    @Override
    public String toString() {
        return "AddSessionRequest{" +
                "participantId=" + participantId +
                ", sessionId=" + sessionId +
                ", sessionType='" + sessionType + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
