package kr.co.doritos.todoservice.common;

public enum ResponseCode {

    Success("0000", "Success"),

    E3001("3001", "로그인 실패"),
    E4001("4001", "데이터 조회 실패"),
    E5001("5001", "System Error"),
    ;

    private final String code;
    private final String desc;

    ResponseCode(String code, String desc) {
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