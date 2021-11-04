package com.example.aismschatbackend.communication.request;

import java.io.Serializable;

public class AddPrimaryAssessmentRequest implements Serializable {
    public Long participantId;
    public String assessmentType;
    public String date;
    public String startTime;
    public String duration;

    @Override
    public String toString() {
        return "AddPrimaryAssessmentRequest{" +
                "participantId=" + participantId +
                ", assessmentType='" + assessmentType + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
