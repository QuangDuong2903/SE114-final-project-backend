package com.quangduong.SE114backend.payload.response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String url;

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getUrl() {
        return url;
    }

    public ErrorResponse(int status, String error, String url) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.timestamp = dateFormat.format(new Date());
        this.status = status;
        this.error = error;
        this.url = url;
    }
}
