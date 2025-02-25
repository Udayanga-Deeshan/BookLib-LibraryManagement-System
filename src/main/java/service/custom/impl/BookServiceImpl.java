package service.custom.impl;

import com.google.inject.Inject;
import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.BookDao;
import service.custom.BookService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Inject
    BookDao dto;
    @Override
    public boolean addBook(Book book) {
        System.out.println("service  "+book);
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);

       return dto.save(bookEntity);


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
}
