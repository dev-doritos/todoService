package kr.co.doritos.todoservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private long id;
    private UseStatus status;
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private String gender;
    private List<Todo> todoList;

    public Member toEntity() {
        return Member.builder()
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
