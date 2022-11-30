package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.dto.TodoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName(value = "TodoService 통합테스트")
    public void totalTest() {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("장찬양")
                .email("wognsl34@gmail.com")
                .gender("M")
                .password("123456")
                .status(UseStatus.Y)
                .build();

        // FK
        memberDTO = memberService.save(memberDTO);

        TodoDTO todoDTO = TodoDTO.builder()
                .member(memberDTO)
                .status(TodoStatus.Ready)
                .todo("테스트 준비")
                .build();

        TodoDTO saveTodoDTO = todoService.save(todoDTO);

        // when
        List<TodoDTO> todoDTOList = todoService.findAll();
        TodoDTO findTodo = todoService.findById(saveTodoDTO.getId());

        // then
        assertEquals(saveTodoDTO, findTodo);
    }
}
