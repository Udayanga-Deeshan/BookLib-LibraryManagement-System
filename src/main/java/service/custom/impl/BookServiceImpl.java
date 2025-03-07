package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.BookDao;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {


    BookDao dto= new BookDaoImpl();
    @Override
    public boolean addBook(Book book) {
        System.out.println("service  "+book);
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);

       return dto.save(bookEntity);


    }

    @Override
    public boolean updateBook(Book book) {
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);
       return dto.update(bookEntity);
    }

    @Override
    public Book searchBook(String id) {
        BookEntity searchedBook = dto.search(id);
        if(searchedBook!=null){
            return new ModelMapper().map(searchedBook, Book.class);
        }

        return null;

    }

    @Override
    public List<Book> getAll() {
        List<BookEntity> bookEntityList = dto.getAll();
        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity bookEntity: bookEntityList){
            books.add(new ModelMapper().map(bookEntity,Book.class));
        }

        return  books;


    }

    @Override
    public boolean deleteBook(String id) {
        return false;
    }

    @Override
    public ObservableList<String> getBookIds() {
        return dto.getBookCodes();

    }
}
