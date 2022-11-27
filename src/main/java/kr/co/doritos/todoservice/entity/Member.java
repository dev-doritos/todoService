package kr.co.doritos.todoservice.entity;

import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private UseStatus status;

    private String email;
    private String password;

    private String name;

    @Column(length = 1)
    private String gender;

    @OneToMany
    private List<Todo> todoList;

    public MemberDTO toDto() {
        return MemberDTO.builder()
                .id(this.id)
                .status(this.status)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .gender(this.gender)
                .todoList(this.todoList)
                .build();
    }
}
