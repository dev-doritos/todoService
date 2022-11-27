package kr.co.doritos.todoservice.repository;

import kr.co.doritos.todoservice.common.UseStatus;
import kr.co.doritos.todoservice.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    public List<Member> findByStatus(UseStatus status);
    public List<Member> findByName(String name);
}
