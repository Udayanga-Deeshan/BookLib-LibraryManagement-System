package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.BookDao;
import service.custom.BookService;
import util.DaoType;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao dto = DaoFactory.getInstance().getDaoType(DaoType.BOOK);
    @Override
    public boolean addBook(Book book) {
        System.out.println("service  "+book);
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);

        dto.save(bookEntity);

        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public Book searchBook(String id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public boolean deleteBook(String id) {
        return false;
    }
}
