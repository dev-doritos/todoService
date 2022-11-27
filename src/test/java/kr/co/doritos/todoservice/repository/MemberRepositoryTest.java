package kr.co.doritos.todoservice.repository;

import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    public void save() {
        // given
        Member member = Member.builder()
                .id(0)
                .name("장찬양")
                .gender("M")
                .email("wognsl34@daum.net")
                .password("12345")
                .status(UseStatus.Y)
                .todoList(new ArrayList<Todo>())
                .build();

        // when
        Member member_ = memberRepository.save(member);

        // then
        assertEquals(member, member_);
    }
    
    @Test
    public void findByName() {
        // given
        save();
        
        // when
        Member member = memberRepository.findByName("장찬양").get(0);
        
        // then
        assertNotNull(member);
        assertEquals(member.getName(), "장찬양");
    }
}