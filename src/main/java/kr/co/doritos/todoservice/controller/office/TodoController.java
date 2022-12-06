package kr.co.doritos.todoservice.controller.office;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.TodoStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.dto.TodoDTO;
import kr.co.doritos.todoservice.exception.JsonTodoException;
import kr.co.doritos.todoservice.service.MemberService;
import kr.co.doritos.todoservice.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "OfficeTodoController")
@RequestMapping(value = "/office/")
@Slf4j
public class TodoController {

    private final TodoService todoService;
    private final MemberService memberService;

    public TodoController(TodoService todoService, MemberService memberService) {
        this.todoService = todoService;
        this.memberService = memberService;
    }
    
    @GetMapping(value = "/todoList")
    public ModelAndView toTodoListPage(HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] todoList 페이지 접근", ip);

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

        log.info("[{}][office] todo 페이지 접근. {}", ip, id);

        ModelAndView mav = new ModelAndView();

        TodoDTO todoDTO = todoService.findById(Long.parseLong(id));

        mav.addObject("todoDTO", todoDTO);
        mav.addObject("todoStatus", TodoStatus.values());

        mav.setViewName("office/todo/view");

        return mav;
    }

    @GetMapping(value = "/todo")
    public ModelAndView toRegistTodoPage(HttpServletRequest request) {
        
        String ip = request.getRemoteAddr();
        
        log.info("[{}][office] todo 등록 페이지 접근.", ip);
        
        ModelAndView mav = new ModelAndView();

        mav.addObject("todoStatus", TodoStatus.values());

        mav.setViewName("office/todo/regist");
        
        return mav;
    }

    @PostMapping(value = "/todo")
    @ResponseBody
    public String toRegistTodo(HttpServletRequest request, @RequestBody TodoDTO todoDTO) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] todo 등록 접근. {} ", ip, todoDTO);

        String deadline = todoDTO.getDeadline();
        deadline = deadline.replace("-", "");
        todoDTO.setDeadline(deadline);

        /*
        * TODO 변경
        * */
        MemberDTO doritos = memberService.findById(1);
        todoDTO.setMember(doritos);

        TodoDTO saveTodoDTO = todoService.save(todoDTO);

        log.info("[{}][office] todo 등록 완료. {}", ip, saveTodoDTO);

        JSONObject response = new JSONObject();

        try {
            response.put("res_cd", ResponseCode.Success.getCode());
            response.put("res_msg", ResponseCode.Success.getDesc());
            response.put("res_data", new ObjectMapper().writeValueAsString(saveTodoDTO));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
            throw new JsonTodoException(ResponseCode.E5001);
        }

        return response.toString();
    }

    @PutMapping(value = "/todo/{id}")
    @ResponseBody
    public String toUpdateTodo(HttpServletRequest request, @PathVariable(value = "id") String id, @RequestBody TodoDTO todoDTO) {
        
        String ip = request.getRemoteAddr();

        log.info("[{}][office] todo 수정 접근. {} ", ip, todoDTO);

        String deadline = todoDTO.getDeadline();
        deadline = deadline.replace("-", "");
        todoDTO.setDeadline(deadline);

        boolean isExists = todoService.existsById(Long.parseLong(id));

        if (!isExists) {
            throw new JsonTodoException(ResponseCode.E4001);
        }

        TodoDTO saveTodoDTO = todoService.update(todoDTO);

        log.info("[{}][office] todo 수정 완료. {}", ip, saveTodoDTO);

        JSONObject response = new JSONObject();

        try {
            response.put("res_cd", ResponseCode.Success.getCode());
            response.put("res_msg", ResponseCode.Success.getDesc());
            response.put("res_data", new ObjectMapper().writeValueAsString(saveTodoDTO));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
            throw new JsonTodoException(ResponseCode.E5001);
        }

        return response.toString();
    }

}
