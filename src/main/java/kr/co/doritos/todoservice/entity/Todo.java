package kr.co.doritos.todoservice.entity;

import kr.co.doritos.todoservice.common.TodoStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 4000)
    private String todo;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TodoStatus status;

    @ManyToOne
    private Member member;
}
