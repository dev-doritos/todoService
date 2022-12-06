package kr.co.doritos.todoservice.controller.office;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.exception.JsonTodoException;
import kr.co.doritos.todoservice.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "OfficeMemberController")
@RequestMapping(value = "/office/")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "memberList")
    public ModelAndView toMemberListPage(HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] todoList 페이지 접근", ip);

        ModelAndView mav = new ModelAndView();

        // MemberList 조회
        List<MemberDTO> memberDTOList = memberService.findAll();

        log.info("memberDTOList={}", memberDTOList);

        mav.addObject("memberDTOList", memberDTOList);

        mav.setViewName("office/member/list");

        return mav;
    }

    @GetMapping(value = "/member/{id}")
    public ModelAndView toMemberPage(HttpServletRequest request, @PathVariable(name = "id") String id) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] member 페이지 접근. {}", ip, id);

        ModelAndView mav = new ModelAndView();

        MemberDTO memberDTO = memberService.findById(Long.parseLong(id));

        mav.addObject("memberDTO", memberDTO);
        mav.addObject("userRole", UserRole.values());
        mav.addObject("useStatus", UseStatus.values());

        mav.setViewName("office/member/view");

        return mav;
    }

    @GetMapping(value = "member")
    public ModelAndView toRegistMemberPage(HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] member 등록 페이지 접근.", ip);

        ModelAndView mav = new ModelAndView();

        mav.addObject("userRole", UserRole.values());
        mav.addObject("useStatus", UseStatus.values());

        mav.setViewName("office/member/regist");

        return mav;
    }

    @PostMapping(value = "/member")
    @ResponseBody
    public String toRegistMember(HttpServletRequest request, @RequestBody MemberDTO memberDTO) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] member 등록 접근. {} ", ip, memberDTO);

        MemberDTO saveMemberDTO = memberService.save(memberDTO);

        log.info("[{}][office] member 등록 완료. {}", ip, saveMemberDTO);

        JSONObject response = new JSONObject();

        try {
            response.put("res_cd", ResponseCode.Success.getCode());
            response.put("res_msg", ResponseCode.Success.getDesc());
            response.put("res_data", new ObjectMapper().writeValueAsString(saveMemberDTO));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
            throw new JsonTodoException(ResponseCode.E5001);
        }

        return response.toString();
    }

    @PutMapping(value = "/member/{id}")
    @ResponseBody
    public String toUpdateMember(HttpServletRequest request, @PathVariable(value = "id") String id, @RequestBody MemberDTO memberDTO) {

        String ip = request.getRemoteAddr();

        log.info("[{}][office] member 수정 접근. {} ", ip, memberDTO);

        boolean isExists = memberService.existsById(Long.parseLong(id));

        if (!isExists) {
            throw new JsonTodoException(ResponseCode.E4001);
        }

        // 비밀번호 변경 하지 않았을 경우,
        if (memberDTO.getPassword() == null || memberDTO.getPassword().equals("")) {
            String orgPassword = memberService.findById(Long.parseLong(id)).getPassword();
            memberDTO.setPassword(orgPassword);
            log.info("[{}][office] member 비밀번호 변경하지 않음.", ip);
        }

        MemberDTO saveMemberDTO = memberService.save(memberDTO);

        log.info("[{}][office] member 수정 완료. {}", ip, saveMemberDTO);

        JSONObject response = new JSONObject();

        try {
            response.put("res_cd", ResponseCode.Success.getCode());
            response.put("res_msg", ResponseCode.Success.getDesc());
            response.put("res_data", new ObjectMapper().writeValueAsString(saveMemberDTO));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
            throw new JsonTodoException(ResponseCode.E5001);
        }

        return response.toString();
    }
}
