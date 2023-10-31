package br.com.devairon.backend.backend_my_rent.exception;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
