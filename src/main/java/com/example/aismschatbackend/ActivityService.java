package com.example.aismschatbackend;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ActivityService {

    private final ArrayList<String> questionSet = new ArrayList<>();

    public ActivityService() {
        questionSet.add("Hello! Welcome to AI-SMS Chat System. Press any key to start the session.");
        questionSet.add("Are you having trouble with another person?");
        questionSet.add("How would someone who was not involved describe it?");
        questionSet.add("How do you see it?");
        questionSet.add("Why do you see it that way?");
        questionSet.add("How does the other person see it?");
        questionSet.add("How does the other person see it that way?");
        questionSet.add("What can you do about it?");
    }

    public String getNextQuestion() {
        String question = questionSet.get(0);
        questionSet.remove(0);
        return question;
    }
}
