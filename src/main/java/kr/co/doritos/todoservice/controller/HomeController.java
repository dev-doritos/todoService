package kr.co.doritos.todoservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class HomeController {

    @GetMapping(value = "/")
    public String toIndex(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        log.info("[{}][front] index 페이지 접근", ip);
        return "index";
    }

    @GetMapping(value = "/login")
    public String toLoginPage(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        log.info("[{}][front] login 페이지 접근", ip);
        return "login";
    }
}
