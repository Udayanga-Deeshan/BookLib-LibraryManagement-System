package repository.custom.impl;

import dto.Book;
import entity.BookEntity;
import repository.CRUDRepository;
import repository.custom.BookDao;

import java.util.List;

public class BookDaoImpl  implements BookDao {

    @Override
    public boolean save(BookEntity entity) {
        System.out.println("Repository "+entity);
        return  false;
    }

    @Override
    public boolean update(BookEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<BookEntity> getAll() {
        return List.of();
    }

    @Override
    public BookEntity search(String s) {
        return null;
    }
}
