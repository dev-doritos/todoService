package kr.co.doritos.todoservice.common;

public enum UserRole {

    ROLE_ADMIN("ADMIN", "ROLE_ADMIN", "관리자"),
    ROLE_GENERAL("GENERAL", "ROLE_GENERAL", "일반"),
    ;

    private final String code;
    private final String role;
    private final String desc;

    UserRole(String code, String role, String desc) {
        this.code = code;
        this.role = role;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }
    public String getRole() { return this.role; }
    public String getDesc() {
        return this.desc;
    }
}
