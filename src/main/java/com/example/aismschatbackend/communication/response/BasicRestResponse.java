package com.example.aismschatbackend.communication.response;

import java.io.Serializable;

public class BasicRestResponse implements Serializable {

    public String message;

    public BasicRestResponse(String message) {
        this.message = message;
    }

    public BasicRestResponse() {

    }

    @Override
    public String toString() {
        return "BasicRestResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
