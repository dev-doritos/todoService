package kr.co.doritos.todoservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import kr.co.doritos.todoservice.entity.QTodo;
import kr.co.doritos.todoservice.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TodoSearchRepositoryImplTest {

    private TodoSearchRepository todoSearchRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    public TodoSearchRepositoryImplTest(TodoSearchRepository todoSearchRepository) {
        this.todoSearchRepository = todoSearchRepository;
    }

    @Test
    public void searchAll() {
        QTodo todo = QTodo.todo;
        List<Todo> todoList = queryFactory
                                .selectFrom(todo)
                                .where(todo.member.email.gt("doritos"))
                                .fetch();

        assertNotNull(todoList);
        todoList.stream().forEach(e -> {
            System.err.println(e.toDTO());
        });
    }
}