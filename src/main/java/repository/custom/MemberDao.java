package repository.custom;

import entity.MemberEntity;
import javafx.collections.ObservableList;
import repository.CRUDRepository;

public interface MemberDao  extends CRUDRepository<MemberEntity,String> {
    ObservableList<String> getMemberIds();
}
