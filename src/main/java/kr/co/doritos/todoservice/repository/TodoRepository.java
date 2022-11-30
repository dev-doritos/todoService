package kr.co.doritos.todoservice.repository;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.dto.TodoDTO;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    public List<Todo> findAllByOrderByIdDesc();

    public List<Todo> findByStatus(TodoStatus status);

    public List<Todo> findByMember(Member member);
}
