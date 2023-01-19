package com.epaylater.app.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(int code, String message){
        super();
        this.statusCode = code;
        this.message = message;
    }
}
