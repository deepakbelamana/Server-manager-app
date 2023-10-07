package com.ServerManager.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected String message;
    protected String developerMessage;

    protected String reason;
    protected int statusCode;
    protected HttpStatus status;
    protected Map<?, ?> data;

}
