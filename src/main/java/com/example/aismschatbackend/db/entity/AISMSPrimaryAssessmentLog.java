package com.example.aismschatbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ai_sms_primary_assessment_log")
public class AISMSPrimaryAssessmentLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "participant_id")
    public Long participantId;

    @Column(name = "assessment_type")
    public String assessmentType;

    @Column(name = "status")
    public String status;

    @Column(name = "meeting_id")
    public Long meetingId;

    @Column(name = "meeting_url")
    public String meetingURL;

    @Column(name = "meeting_start_time")
    public Long meetingStartTime;

    @Column(name = "meeting_duration")
    public String meetingDuration;

    @Column(name = "actual_end_time")
    public Long actualEndTime;

    public AISMSPrimaryAssessmentLog() {

    }

    @Override
    public String toString() {
        return "AISMSPrimaryAssessmentLog{" +
                "id=" + id +
                ", participantId=" + participantId +
                ", assessmentType='" + assessmentType + '\'' +
                ", status='" + status + '\'' +
                ", meetingId=" + meetingId +
                ", meetingURL='" + meetingURL + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingDuration='" + meetingDuration + '\'' +
                ", actualEndTime=" + actualEndTime +
                '}';
    }
}
