package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.exception.TodoException;
import kr.co.doritos.todoservice.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements  MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDTO> findAll() {
        List<Member> memberList = (List<Member>) memberRepository.findAll();
        return toMemberDTOList(memberList);
    }

    @Override
    public List<MemberDTO> findByStatus(UseStatus status) {
        List<Member> memberList = (List<Member>) memberRepository.findByStatus(status);

        if(memberList == null || memberList.isEmpty()) {
            throw new TodoException(ResponseCode.E4001);
        }

        return toMemberDTOList(memberList);
    }

    @Override
    public List<MemberDTO> findByName(String name) {
        List<Member> memberList = (List<Member>) memberRepository.findByName(name);

        if(memberList == null || memberList.isEmpty()) {
            throw new TodoException(ResponseCode.E4001);
        }

        return toMemberDTOList(memberList);
    }

    @Override
    public MemberDTO findById(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);

        if(memberOptional.isEmpty()) {
            throw new TodoException(ResponseCode.E4001);
        }

        return memberOptional.get().toDto();
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        Member member = memberRepository.save(memberDTO.toEntity());
        return member.toDto();
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO) {
        Member member = memberRepository.save(memberDTO.toEntity());
        return member.toDto();
    }

    @Override
    public void deleteById(long id) {
        memberRepository.deleteById(id);
    }

    private List<MemberDTO> toMemberDTOList(List<Member> memberList) {
        return memberList.stream()
                .map(Member::toDto)
                .collect(Collectors.toList());
    }
}
