package com.example.aismschatbackend.communication.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OKResponse extends ResponseEntity<BasicRestResponse>{
    private Logger log = LoggerFactory.getLogger(OKResponse.class);

    public OKResponse(BasicRestResponse body) {
        super(body, HttpStatus.OK);
        this.getBody().message = body.message == null ? "Operation successful" : body.message;
        this.log.info("Responding: 200, {}", this.getBody());

    }
    public OKResponse() {
        super(new BasicRestResponse("Operation successful"), HttpStatus.OK);
        this.log.info("Responding: 200, {}", this.getBody());
    }

    public OKResponse(String message) {
        super(new BasicRestResponse(message), HttpStatus.OK);
        this.log.info("Responding: 200, {}", this.getBody());
    }

}