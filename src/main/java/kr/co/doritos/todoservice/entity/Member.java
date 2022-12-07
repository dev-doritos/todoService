package kr.co.doritos.todoservice.entity;

import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.dto.MemberDTO;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private UseStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserRole userRole;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(length = 1)
    private String gender;

    public MemberDTO toDto() {
        return MemberDTO.builder()
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
