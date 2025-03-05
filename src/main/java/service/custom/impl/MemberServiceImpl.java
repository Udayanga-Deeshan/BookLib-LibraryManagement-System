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

     return   dao.update(new ModelMapper().map(member,MemberEntity.class));
    }

    @Override
    public Member searchMember(String id) {
        MemberEntity search = dao.search(id);
        return new ModelMapper().map(search, Member.class);

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
