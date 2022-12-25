package kr.co.doritos.todoservice.repository;

import kr.co.doritos.todoservice.entity.Todo;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TodoSearchRepository {

    public List<Todo> searchAll(Pageable pageable);
}
