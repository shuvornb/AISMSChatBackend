package com.example.aismschatbackend.db.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "ai_sms_activity")
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class AISMSActivity implements Serializable {
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

    public AISMSActivity() {

    }

    public AISMSActivity(Long id, String title, String description, Map<String, Object> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "AISMSActivity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }
}
