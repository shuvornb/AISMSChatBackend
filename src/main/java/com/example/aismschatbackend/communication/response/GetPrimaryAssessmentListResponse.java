package com.example.aismschatbackend.communication.response;

import com.example.aismschatbackend.db.entity.AISMSPrimaryAssessmentLog;

import java.util.List;

public class GetPrimaryAssessmentListResponse extends BasicRestResponse{
    public Integer assessmentCount;
    public List<AISMSPrimaryAssessmentLog> primaryAssessmentLogList;

    public GetPrimaryAssessmentListResponse() {

    }

    @Override
    public String toString() {
        return "GetPrimaryAssessmentListResponse{" +
                "assessmentCount=" + assessmentCount +
                ", primaryAssessmentLogList=" + primaryAssessmentLogList +
                ", message='" + message + '\'' +
                '}';
    }
}
