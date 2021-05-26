package com.example.aismschatbackend.communication.response;

public class AudioSyncResponse extends BasicRestResponse{
    public String fileName;

    public AudioSyncResponse() {

    }

    public AudioSyncResponse(String message, String fileName) {
        super(message);
        this.fileName = fileName;
    }

    public AudioSyncResponse(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "AudioSyncResponse{" +
                "fileName='" + fileName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
