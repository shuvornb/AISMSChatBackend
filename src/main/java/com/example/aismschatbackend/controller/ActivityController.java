package com.example.aismschatbackend.controller;

import com.example.aismschatbackend.communication.request.SaveActivityRequest;
import com.example.aismschatbackend.communication.response.AudioSyncResponse;
import com.example.aismschatbackend.communication.response.GetActivityListResponse;
import com.example.aismschatbackend.communication.response.OKResponse;
import com.example.aismschatbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    @RequestMapping(value = "/sync/audio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AudioSyncResponse syncAudioResponse(@RequestParam("file") MultipartFile file, @RequestParam("participantId") Long participantId, @RequestParam("activityId") Long activityId) throws IOException {
        String fileName = participantId + "_" + activityId + "_" + System.currentTimeMillis() + ".m4a";
        Path path = Paths.get("C:\\Users\\shuvo\\Desktop\\Audio\\" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AudioSyncResponse("Successfully synced the audio response", fileName);
    }
}
