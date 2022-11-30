package kr.co.doritos.todoservice.controller.office;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.dto.TodoDTO;
import kr.co.doritos.todoservice.exception.TodoException;
import kr.co.doritos.todoservice.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "OfficeTodoController")
@RequestMapping(value = "/office/")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping(value = "/todo")
    public ModelAndView toTodoListPage(HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        log.info("[{}] 관리자 todoList 페이지 접근", ip);

        ModelAndView mav = new ModelAndView();

        // TodoList 조회
        List<TodoDTO> todoDTOList = todoService.findAllByOrderByIdDesc();

        log.info("todoList={}", todoDTOList);

        mav.addObject("todoDTOList", todoDTOList);

        mav.setViewName("office/todo/list");

        return mav;
    }

    @GetMapping(value = "/todo/{id}")
    public ModelAndView toTodoPage(HttpServletRequest request, @PathVariable(value = "id") String id) {

        String ip = request.getRemoteAddr();

        log.info("[{}] 관리자 todo({}) 페이지 접근", ip, id);

        ModelAndView mav = new ModelAndView();

        TodoDTO todoDTO = todoService.findById(Long.parseLong(id));

        mav.addObject("todo", todoDTO);

        mav.setViewName("office/todo/view");

        return mav;
    }
}
