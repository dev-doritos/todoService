package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.dto.TodoDTO;
import kr.co.doritos.todoservice.entity.Todo;
import kr.co.doritos.todoservice.exception.TodoException;
import kr.co.doritos.todoservice.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    @Override
    public List<TodoDTO> findAll() {
        List<Todo> todoList = (List<Todo>) todoRepository.findAll();
        return toMemberDTOList(todoList);
    }

    public List<TodoDTO> findAllByOrderByIdDesc() {
        List<Todo> todoList = (List<Todo>) todoRepository.findAllByOrderByIdDesc();
        return toMemberDTOList(todoList);
    }

    @Override
    public List<TodoDTO> findByStatus(TodoStatus status) {
        List<Todo> todoList = (List<Todo>) todoRepository.findByStatus(status);
        return toMemberDTOList(todoList);
    }

    @Override
    public List<TodoDTO> findByMember(MemberDTO memberDTO) {
        List<Todo> todoList = (List<Todo>) todoRepository.findByMember(memberDTO.toEntity());
        return toMemberDTOList(todoList);
    }

    @Override
    public TodoDTO findById(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoException(ResponseCode.E4001));
        return todo.toDTO();
    }

    @Override
    public TodoDTO save(TodoDTO todoDTO) {
        return todoRepository.save(todoDTO.toEntity()).toDTO();
    }

    @Override
    public TodoDTO update(TodoDTO todoDTO) {
        return todoRepository.save(todoDTO.toEntity()).toDTO();
    }

    @Override
    public void deleteById(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return todoRepository.existsById(id);
    }

    private List<TodoDTO> toMemberDTOList(List<Todo> todoList) {
        return todoList.stream()
                .map(Todo::toDTO)
                .collect(Collectors.toList());
    }
}
