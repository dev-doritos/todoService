package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.exception.TodoException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Disabled
    void findAll() {
        System.err.println(memberService.findAll());
        assertNotNull(memberService.findAll());
    }

    @Test
    void findByStatus() {
        // given
        UseStatus status = UseStatus.Y;
        ResponseCode code = null;

        // when
        try {
            List<MemberDTO> memberList = memberService.findByStatus(status);
        } catch(TodoException e) {
            code = e.getCode();
        }

        // then
        assertEquals(code, ResponseCode.E4001);
    }
}