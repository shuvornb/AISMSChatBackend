package com.example.aismschatbackend.db.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "ai_sms_activity_response")
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class AISMSActivityResponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "participant_id")
    public Long participantId;

    @Column(name = "activity_id")
    public Long activityId;

    @Column(name = "response", columnDefinition = "jsonb")
    @Type(type = "JsonbType")
    public Map<String, Object> response;

    @Column(name = "response_time")
    public Long responseTime;

    public AISMSActivityResponse() {

    }

    public AISMSActivityResponse(Long id, Long participantId, Long activityId, Map<String, Object> response, Long responseTime) {
        this.id = id;
        this.participantId = participantId;
        this.activityId = activityId;
        this.response = response;
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "AISMSActivityResponse{" +
                "id=" + id +
                ", participantId=" + participantId +
                ", activityId=" + activityId +
                ", response=" + response +
                ", responseTime=" + responseTime +
                '}';
    }
}
