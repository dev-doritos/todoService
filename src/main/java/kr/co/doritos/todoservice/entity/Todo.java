package kr.co.doritos.todoservice.entity;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.dto.TodoDTO;
import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private long id;

    @Column(length = 4000)
    private String todo;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TodoStatus status;

    private String deadline;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public TodoDTO toDTO() {
        return TodoDTO.builder()
                .id(this.id)
                .todo(this.todo)
                .status(this.status)
                .deadline(this.deadline)
                .member(this.member.toDto())
                .build();
    }
}
