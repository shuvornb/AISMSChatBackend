package com.example.aismschatbackend.db.entity;

import com.example.aismschatbackend.communication.request.FiveKeyAssessRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "ai_sms_rwat")
public class AISMSRWAT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "assess_date")
    public LocalDateTime assessDate;

    @Column(name = "assess_result")
    public String assessResult;

    @Column(name = "cog_flex_score")
    public int cogFlexScore;

    @Column(name = "empathy_score")
    public int empathyScore;

    @Column(name = "hope_score")
    public int hopeScore;

    @Column(name = "p_cope_score")
    public int pCopeScore;

    @Column(name = "e_cope_score")
    public int eCopeScore;

    @Column(name = "d_cope_score")
    public int dCopeScore;

    @Column(name = "positive_relation_count")
    public int positiveRelationCount;

    @Column(name = "positive_relation_percent")
    public int positiveRelationPercent;

    @Column(name = "cloest_relation_score")
    public double cloestRelationScore;

    @Column(name = "most_time_relation_score")
    public double mostTimeRelationScore;

    @Column(name = "perception_of_worth_score")
    public int perceptionOfWorthScore;

    @Column(name = "daily_activity")
    public String dailyActivity;

    @Column(name = "com_and_leisure_score")
    public int comAndLeisureScore;

    @Column(name = "education_score")
    public double educationScore;

    @Column(name = "employ_score")
    public double employScore;

    public AISMSRWAT(FiveKeyAssessRequest r, Long userId) {
        this.userId = userId;
        assessDate = LocalDateTime.now();
        assessResult = r.getRawResult();
        cogFlexScore = r.getCogFlexScore();
        empathyScore = r.getEmpathyScore();
        hopeScore = r.getHopeScore();
        pCopeScore = r.getCopScore(0);
        eCopeScore = r.getCopScore(1);
        dCopeScore = r.getCopScore(2);
        int[] socialNetworkScore = r.getSocialNetworkScore();
        positiveRelationCount = socialNetworkScore[0];
        positiveRelationPercent = socialNetworkScore[1];
        double[] qualiftOfRelation = r.getQualityOfRelation();
        cloestRelationScore = qualiftOfRelation[0];
        mostTimeRelationScore = qualiftOfRelation[1];
        perceptionOfWorthScore = r.getPerceptionOfWorthScore();
        dailyActivity = r.getDailyActivity();
        comAndLeisureScore = r.getComAndLeisureScore();
        educationScore = r.getEducationScore();
        employScore = r.getEmployScore();
    }

    public AISMSRWAT() {

    }
}