package com.example.aismschatbackend.controller;

import com.example.aismschatbackend.communication.request.AddPrimaryAssessmentRequest;
import com.example.aismschatbackend.communication.request.AddSessionRequest;
import com.example.aismschatbackend.communication.request.FiveKeyAssessRequest;
import com.example.aismschatbackend.communication.response.GetPrimaryAssessmentListResponse;
import com.example.aismschatbackend.communication.response.GetSessionListResponse;
import com.example.aismschatbackend.communication.response.OKResponse;
import com.example.aismschatbackend.service.PrimaryAssessmentService;
import com.example.aismschatbackend.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nij/ai-sms/reentry")
public class FiveKeyReentryController {

    @Autowired
    private PrimaryAssessmentService primaryAssessmentService;

    @Autowired
    private SessionService sessionService;

    Logger logger = LoggerFactory.getLogger(FiveKeyReentryController.class);

    @RequestMapping(value = "primary-assessment/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPrimaryAssessmentListResponse getPrimaryAssessmentList(@RequestParam("participantId") Long participantId) {
        logger.info("ParticipantId: " + participantId);
        return primaryAssessmentService.getPrimaryAssessmentList(participantId);
    }

    @RequestMapping(value = "primary-assessment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse addPrimaryAssessment(@RequestBody AddPrimaryAssessmentRequest request) {
        logger.info(request.toString());
        primaryAssessmentService.addPrimaryAssessment(request);
        return new OKResponse("Successfully added primary assessment");
    }

    @RequestMapping(value = "session/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetSessionListResponse getSessionList(@RequestParam("participantId") Long participantId) {
        logger.info("ParticipantId: " + participantId);
        return sessionService.getSessionList(participantId);
    }

    @RequestMapping(value = "session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse addSession(@RequestBody AddSessionRequest request) {
        logger.info(request.toString());
        sessionService.addSession(request);
        return new OKResponse("Successfully added a session.");
    }

    @RequestMapping(value = "primary-assessment/rwat", method = RequestMethod.POST)
    public OKResponse updateRWATAssessment(@RequestBody FiveKeyAssessRequest fiveKey) {
        logger.info("Update user 5key assessment api invoked");
        primaryAssessmentService.updateRWATResult((long) 50, fiveKey);
        return new OKResponse("Successfully updated the RWAT assessment");
    }
}
