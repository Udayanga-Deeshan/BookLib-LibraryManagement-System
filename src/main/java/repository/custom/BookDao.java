package repository.custom;

import dto.Book;
import entity.BookEntity;
import javafx.collections.ObservableList;
import repository.CRUDRepository;

public interface BookDao  extends CRUDRepository<BookEntity, String > {
    ObservableList<String> getBookCodes();
}
