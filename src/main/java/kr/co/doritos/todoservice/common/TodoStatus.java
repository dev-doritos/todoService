package kr.co.doritos.todoservice.common;

public enum TodoStatus {

    Ready("Ready", "준비"),
    Progress("Progress", "진행 중"),
    Done("Done", "완료"),
    ;

    private final String code;
    private final String desc;

    TodoStatus(String code, String desc) {
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
