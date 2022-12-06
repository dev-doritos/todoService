package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    public List<TodoDTO> findAll();
    public List<TodoDTO> findAllByOrderByIdDesc();
    public List<TodoDTO> findByStatus(TodoStatus status);
    public List<TodoDTO> findByMember(MemberDTO memberDTO);
    public TodoDTO findById(long id);
    public TodoDTO save(TodoDTO todoDTO);
    public TodoDTO update(TodoDTO todoDTO);
    public void deleteById(long id);

    public boolean existsById(long id);
}
