package service.custom;

import dto.Member;

import java.util.List;

public interface MemberService {

    boolean createMember(Member member);

    boolean updateMember(Member member);

    Member searchMember(String id);

    List<Member> getAll();
}
