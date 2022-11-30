package kr.co.doritos.todoservice.controller;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.exception.TodoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        mav.setViewName("office/error");
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception e) {

        // stackTrace 출력
        e.printStackTrace();

        ModelAndView mav = new ModelAndView();

        ResponseCode code = ResponseCode.E5001;

        mav.addObject("code", code);
        mav.addObject("message", code.getMessage());
        mav.setViewName("office/error");

        return mav;
    }
}
