package service.custom;

import dto.Book;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface BookService extends SuperService {

    boolean addBook(Book book);

    boolean updateBook(Book book);

    Book searchBook(String id);

    List<Book> getAll();

    boolean deleteBook(String id);

    ObservableList<String> getBookIds();

}
