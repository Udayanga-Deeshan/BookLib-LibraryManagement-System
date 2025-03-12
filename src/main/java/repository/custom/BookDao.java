package repository.custom;

import dto.Book;
import dto.BorrowDetails;
import entity.BookEntity;
import entity.BorrowDetailsEntity;
import javafx.collections.ObservableList;
import repository.CRUDRepository;

public interface BookDao  extends CRUDRepository<BookEntity, String > {
    ObservableList<String> getBookCodes();

    boolean updateAvailability(BorrowDetailsEntity borrowDetails);
}
