package kr.co.doritos.todoservice.exception;

import kr.co.doritos.todoservice.common.ResponseCode;
import lombok.Getter;

@Getter
public class JsonTodoException extends RuntimeException {

    private ResponseCode code;
    private String message;

    public JsonTodoException() {
        super();
    }

    public JsonTodoException(ResponseCode code) {
        super();
        this.code = code;
        this.message = code.getDesc();
    }

    public JsonTodoException(ResponseCode code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public JsonTodoException(Throwable t) {
        super(t);
        this.code = ResponseCode.E5001;
        this.message = t.getMessage();
    }
}