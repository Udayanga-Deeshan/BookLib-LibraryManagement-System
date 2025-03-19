package repository.custom;

import entity.BorrowDetailsEntity;
import entity.ReturnBookEntity;
import repository.CRUDRepository;

import java.util.List;

public interface ReturnBookDao extends CRUDRepository<ReturnBookEntity,String> {


}
