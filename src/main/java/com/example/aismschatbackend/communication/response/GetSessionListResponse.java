package com.example.aismschatbackend.communication.response;

import java.util.List;

public class GetSessionListResponse extends BasicRestResponse{
    public Integer sessionCount;
    public List<SingleSessionResponse> sessionLogList;

    public GetSessionListResponse() {

    }

    @Override
    public String toString() {
        return "GetSessionListResponse{" +
                "sessionCount=" + sessionCount +
                ", sessionLogList=" + sessionLogList +
                ", message='" + message + '\'' +
                '}';
    }
}
