package kr.co.doritos.todoservice.service;

import kr.co.doritos.todoservice.entity.Member;
import kr.co.doritos.todoservice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("[CustomUserDetailsService] email={}", email);

        Member member = memberRepository.findByEmail(email).get();

        return new User(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getUserRole().getCode()));
    }
}
