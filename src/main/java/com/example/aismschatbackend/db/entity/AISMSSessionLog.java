package com.example.aismschatbackend.db.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "ai_sms_session_log")
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class AISMSSessionLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "participant_id")
    public Long participantId;

    @Column(name = "session_id")
    public Long sessionId;

    @Column(name = "response", columnDefinition = "jsonb")
    @Type(type = "JsonbType")
    public Map<String, Object> response;

    @Column(name = "session_type")
    public String sessionType;

    @Column(name = "status")
    public String status;

    @Column(name = "start_time")
    public Long startTime;

    @Column(name = "end_time")
    public Long endTime;

    @Column(name = "meeting_id")
    public Long meetingId;

    @Column(name = "meeting_url")
    public String meetingURL;

    @Column(name = "meeting_start_time")
    public Long meetingStartTime;

    @Column(name = "meeting_duration")
    public String meetingDuration;

    public AISMSSessionLog() {

    }

    @Override
    public String toString() {
        return "AISMSSessionLog{" +
                "id=" + id +
                ", participantId=" + participantId +
                ", sessionId=" + sessionId +
                ", response=" + response +
                ", sessionType='" + sessionType + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingId=" + meetingId +
                ", meetingURL='" + meetingURL + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingDuration='" + meetingDuration + '\'' +
                '}';
    }
}
