package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.exception.TodoException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName(value = "MemberService 통합테스트")
    @Transactional
    void totalTest() {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("장찬양")
                .gender("M")
                .password("123456")
                .email("wognsl34@gmail.com")
                .status(UseStatus.Y)
                .build();

        MemberDTO saveMemberDTO = memberService.save(memberDTO);

        // when
        List<MemberDTO> memberDTOList = memberService.findAll();
        MemberDTO findMember = memberService.findByName("장찬양").get(0);
        MemberDTO findMember2 = memberService.findById(saveMemberDTO.getId());

        // then
        assertEquals(memberDTOList.size(), 1);
        assertEquals(findMember, saveMemberDTO);
        assertEquals(findMember, findMember2);
    }
}