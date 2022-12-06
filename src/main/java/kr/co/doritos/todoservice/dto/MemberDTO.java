package kr.co.doritos.todoservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private long id;
    private UseStatus status;
    private UserRole userRole;
    private String email;
    private String password;
    private String name;
    private String gender;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

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
