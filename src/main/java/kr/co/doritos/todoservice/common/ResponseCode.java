package kr.co.doritos.todoservice.common;

public enum ResponseCode {

    Success("0000", "Success"),

    E4001("4001", "데이터 조회 실패"),
    E5001("5001", "System Error"),
    ;

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}