package service.custom.impl;

import com.google.inject.Inject;
import dto.Member;
import entity.MemberEntity;
import org.modelmapper.ModelMapper;
import repository.custom.MemberDao;
import service.custom.MemberService;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    @Inject
    MemberDao dao;
    @Override
    public boolean createMember(Member member) {
        MemberEntity map = new ModelMapper().map(member, MemberEntity.class);
        return dao.save(map);

    }

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

    @Override
    public Member searchMember(String id) {
        return null;
    }

    @Override
    public List<Member> getAll() {
        List<MemberEntity> all = dao.getAll();
        List<Member> memberList = new ArrayList<>();
        for(MemberEntity member: all){
            memberList.add(new ModelMapper().map(member,Member.class));
        }

        return  memberList;
    }
}
