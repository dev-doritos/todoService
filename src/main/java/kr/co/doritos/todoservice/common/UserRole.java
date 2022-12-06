package kr.co.doritos.todoservice.common;

public enum UserRole {

    ADMIN("ADMIN", "관리자"),
    GENERAL("GENERAL", "일반"),
    ;

    private final String code;
    private final String desc;

    UserRole(String code, String desc) {
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
