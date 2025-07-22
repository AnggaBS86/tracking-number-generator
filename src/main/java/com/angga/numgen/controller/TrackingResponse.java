package com.angga.numgen.controller;

public class TrackingResponse {
    private String tracking_number;
    private String created_at;

    public TrackingResponse(String tracking_number, String created_at) {
        this.tracking_number = tracking_number;
        this.created_at = created_at;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public String getCreated_at() {
        return created_at;
    }
}
