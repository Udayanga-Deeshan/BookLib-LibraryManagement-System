package service.custom;

import dto.Member;
import javafx.collections.ObservableList;

import java.util.List;

public interface MemberService {

    boolean createMember(Member member);

    boolean updateMember(Member member);

    Member searchMember(String id);

    List<Member> getAll();

   ObservableList<String> getMemberIds();

    Member searchMemberData(String id);
}
