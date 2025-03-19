package service.custom;

import dto.Borrow;

public interface BorrowService {

    boolean  borrowBooks(Borrow borrow);

    Borrow findBorrowedBooksById(String id);
}
