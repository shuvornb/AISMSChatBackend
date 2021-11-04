package com.example.aismschatbackend.db.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "ai_sms_session")
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class AISMSSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "questions", columnDefinition = "jsonb")
    @Type(type = "JsonbType")
    public Map<String, Object> questions;

    @Column(name = "associated_key")
    public String associatedKey;

    @Column(name = "intervention_type")
    public String interventionType;

    public AISMSSession() {

    }

    @Override
    public String toString() {
        return "AISMSSession{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", associatedKey='" + associatedKey + '\'' +
                ", interventionType='" + interventionType + '\'' +
                '}';
    }
}
