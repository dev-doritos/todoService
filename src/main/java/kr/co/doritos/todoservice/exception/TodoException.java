package kr.co.doritos.todoservice.exception;

import kr.co.doritos.todoservice.common.ResponseCode;
import lombok.Getter;

@Getter
public class TodoException extends RuntimeException {

    private ResponseCode code;
    private String message;

    public TodoException() {
        super();
    }

    public TodoException(ResponseCode code) {
        super();
        this.code = code;
        this.message = code.getMessage();
    }

    public TodoException(ResponseCode code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public TodoException(Throwable t) {
        super(t);
        this.code = ResponseCode.E5001;
        this.message = t.getMessage();
    }
}