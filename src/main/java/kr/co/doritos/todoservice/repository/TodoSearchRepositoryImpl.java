package kr.co.doritos.todoservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.doritos.todoservice.entity.QTodo;
import kr.co.doritos.todoservice.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TodoSearchRepositoryImpl implements TodoSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public TodoSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Todo> searchAll(Pageable pageable) {
        QTodo todo = QTodo.todo;
        return queryFactory
                .selectFrom(todo)
                .where(todo.member.email.gt("doritos"))
                .fetch();
    }
}
