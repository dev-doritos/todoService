package kr.co.doritos.todoservice.common;

public enum UserRole {

    Admin("Admin", "관리자"),
    Manager("Manager", "운영자"),
    General("General", "일반"),
    ;

    private final String role;
    private final String roleDesc;

    UserRole(String role, String roleDesc) {
        this.role = role;
        this.roleDesc = roleDesc;
    }

    public String getRole() {
        return this.role;
    }

    public String getRoleDesc() {
        return this.roleDesc;
    }
}
