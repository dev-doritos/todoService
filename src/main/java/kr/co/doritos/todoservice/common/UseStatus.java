package kr.co.doritos.todoservice.common;

public enum UseStatus {

    Y("Y", "사용"),
    N("N", "미사용"),
    W("W", "대기");

    private final String code;
    private final String desc;

    private UseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
