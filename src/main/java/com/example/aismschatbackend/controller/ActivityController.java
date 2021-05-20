package com.example.aismschatbackend.controller;

import com.example.aismschatbackend.communication.request.SaveActivityRequest;
import com.example.aismschatbackend.communication.response.GetActivityListResponse;
import com.example.aismschatbackend.communication.response.OKResponse;
import com.example.aismschatbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nij/ai-sms/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetActivityListResponse getActivityList() {
        return activityService.getActivityList();
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse saveActivityResponse(@RequestBody SaveActivityRequest request) {
        activityService.saveActivityResponse(request);
        return new OKResponse("Successfully saved the response");
    }
}
