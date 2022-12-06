package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {

    public List<MemberDTO> findAll();
    public List<MemberDTO> findByStatus(UseStatus status);
    public List<MemberDTO> findByName(String name);
    public MemberDTO findById(long id);
    public MemberDTO save(MemberDTO memberDTO);
    public MemberDTO update(MemberDTO memberDTO);
    public void deleteById(long id);

    public boolean existsById(long id);

}
