package repository.custom;

import entity.BorrowEntity;
import repository.CRUDRepository;

public interface BorrowDao extends CRUDRepository<BorrowEntity,String> {

    BorrowEntity findBorrowedBooksById(String id);

    boolean updateStatusToReturned(String borrowId);

    String getBorrowingStatus(String borrowId);
}
