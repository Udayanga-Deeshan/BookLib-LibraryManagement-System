package service.custom.impl;

import dto.Book;
import service.custom.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public boolean addBook(Book book) {
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
