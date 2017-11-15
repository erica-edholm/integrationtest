package se.omegapoint.store.glue.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorDTO {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

    private ErrorDTO(){
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }


    public void setTimestamp(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.timestamp = LocalDateTime.parse(time, formatter);
    }

    public void setStatus(int value){
        this.status = HttpStatus.valueOf(value);
    }

    public void setError(String error){
        this.error = error;
    }
}
