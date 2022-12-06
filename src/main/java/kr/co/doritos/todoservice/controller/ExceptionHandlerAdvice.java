package kr.co.doritos.todoservice.controller;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.exception.JsonTodoException;
import kr.co.doritos.todoservice.exception.TodoException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = TodoException.class)
    public ModelAndView todoExceptionHandler(TodoException e) {

        // stackTrace 출력
        e.printStackTrace();

        ModelAndView mav = new ModelAndView();

        mav.addObject("code", e.getCode());
        mav.addObject("message", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = JsonTodoException.class)
    @ResponseBody
    public String todoExceptionHandler(JsonTodoException e) {

        // stackTrace 출력
        e.printStackTrace();

        String res_cd = e.getCode().getCode();
        String res_msg = e.getMessage();

        JSONObject response = new JSONObject();
        try {
            response.put("res_cd", res_cd);
            response.put("res_msg", res_msg);
        } catch (JSONException ex) {
            ex.printStackTrace();
            return "";
        }

        return response.toString();
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception e) {

        // stackTrace 출력
        e.printStackTrace();

        ModelAndView mav = new ModelAndView();

        ResponseCode code = ResponseCode.E5001;

        mav.addObject("code", code.getCode());
        mav.addObject("message", code.getDesc());
        mav.setViewName("error");

        return mav;
    }
}
