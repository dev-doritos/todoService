package kr.co.doritos.todoservice.dto;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {

    private long id;
    private String todo;
    private TodoStatus status;
    private String deadline;
    private MemberDTO member;

    public Todo toEntity() {
        return Todo.builder()
                .id(this.id)
                .content(this.todo)
                .status(this.status)
                .deadline(this.deadline)
                .member(this.member.toEntity())
                .build();
    }
}
