package com.exo.demo.response;



import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {

    private int status;
    private String message;
    private Object result;

    public ApiResponse() {
        super();
    }


    public ApiResponse(HttpStatus status, String message) {
        super();
        this.status = status.value();
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, Object result) {
        super();
        this.status = status.value();
        this.message = message;
        this.result = result;
    }




}
