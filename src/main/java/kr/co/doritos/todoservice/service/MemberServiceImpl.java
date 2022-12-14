package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.common.ResponseCode;
import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.dto.MemberDTO;
import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.exception.TodoException;
import kr.co.doritos.todoservice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberServiceImpl implements  MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("[CustomUserDetailsService] email={}", email);

        Member member = memberRepository.findByEmail(email).get();

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        // Use 정보 설정(이름, 비밀번호, 권한)
        return new User(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getUserRole().getRole()));
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
    public MemberDTO findByEmail(String email) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if(memberOptional.isEmpty()) {
            throw new TodoException(ResponseCode.E4001);
        }

        return memberOptional.get().toDto();
    }

    @Override
    @Transactional
    public MemberDTO save(MemberDTO memberDTO) {
        // 비밀번호 암호화
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        Member member = memberRepository.save(memberDTO.toEntity());
        return member.toDto();
    }

    @Override
    @Transactional
    public MemberDTO update(MemberDTO memberDTO) {
        Member member = memberRepository.save(memberDTO.toEntity());
        return member.toDto();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return memberRepository.existsById(id);
    }

    private List<MemberDTO> toMemberDTOList(List<Member> memberList) {
        return memberList.stream()
                .map(Member::toDto)
                .collect(Collectors.toList());
    }
}
