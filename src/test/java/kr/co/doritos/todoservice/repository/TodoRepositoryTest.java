package kr.co.doritos.todoservice.repository;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.entity.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoRepositoryTest {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoRepositoryTest(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Test
    @DisplayName(value = "Todo 통합테스트")
    public void totalTest() {
        // given
        Todo todo = Todo.builder().id(0).todo("통합테스트 해야함").member(null).status(TodoStatus.Ready).build();

        // when
        Todo todo1 = todoRepository.save(todo);
        List<Todo> todoList = (List<Todo>) todoRepository.findAll();
        Todo todo2 = todoRepository.findById(todo1.getId()).get();

        // then
        assertEquals(todo, todo1);
        assertNotNull(todo2);
        //assertEquals(todo1, todo2);
        assertEquals(todoList.size(), 1);

        System.err.println(todoList);
    }
}