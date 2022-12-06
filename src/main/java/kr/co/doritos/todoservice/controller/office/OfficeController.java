package kr.co.doritos.todoservice.controller.office;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class OfficeController {

    @GetMapping(value = "/office")
    public String toOfficeIndexPage(HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] index 페이지 접근", ip);

        return "office/index";
    }

}
