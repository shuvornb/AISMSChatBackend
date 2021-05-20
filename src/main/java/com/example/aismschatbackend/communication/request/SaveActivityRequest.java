package com.example.aismschatbackend.communication.request;

import java.io.Serializable;
import java.util.Map;

public class SaveActivityRequest implements Serializable {
    public Long participantId;
    public Long activityId;
    public Long responseTime;
    public Map<String, Object> response;

    @Override
    public String toString() {
        return "SaveActivityRequest{" +
                "participantId=" + participantId +
                ", activityId=" + activityId +
                ", responseTime=" + responseTime +
                ", response=" + response +
                '}';
    }
}
