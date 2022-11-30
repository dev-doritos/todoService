package kr.co.doritos.todoservice;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.dto.TodoDTO;
import kr.co.doritos.todoservice.service.MemberService;
import kr.co.doritos.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TodoServiceApplication implements CommandLineRunner {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TodoService todoService;

    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // 테스트를 위한 기본 회원 생성
        MemberDTO doritos = MemberDTO.builder()
                .name("doritos")
                .email("wognsl34@gmail.com")
                .gender("M")
                .userRole(UserRole.Admin)
                .status(UseStatus.Y)
                .build();

        MemberDTO saveMember = memberService.save(doritos);

        // 테스트를 위한 기본 TodoList 생성
        TodoDTO todoDTO = TodoDTO.builder()
                .todo("First Todo!")
                .member(saveMember)
                .status(TodoStatus.Ready)
                .build();

        TodoDTO saveTodo = todoService.save(todoDTO);
    }
}