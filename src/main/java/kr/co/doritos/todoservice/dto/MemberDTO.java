package kr.co.doritos.todoservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private long id;
    private UseStatus status;
    private UserRole userRole;
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private String gender;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .status(this.status)
                .userRole(this.userRole)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .gender(this.gender)
                .build();
    }
}
