package kr.co.doritos.todoservice.common;

public enum TodoStatus {

    Ready("Ready", "준비"),
    Progress("Progress", "진행 중"),
    Done("Done", "완료"),
    ;

    private final String status;
    private final String message;

    TodoStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
