package repository.custom.impl;

import db.DBConnection;
import dto.Book;
import entity.BookEntity;
import repository.CRUDRepository;
import repository.custom.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl  implements BookDao {

    @Override
    public boolean save(BookEntity entity) {
        System.out.println("Repository "+entity);
        try {
            String SQL ="INSERT INTO book VALUES(?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getBookId());
            preparedStatement.setObject(2,entity.getISBN());
            preparedStatement.setObject(3,entity.getTitle());
            preparedStatement.setObject(4,entity.getAuthor());
            preparedStatement.setObject(5,entity.getGenre());
            preparedStatement.setObject(6,entity.getAvailability());
            return preparedStatement.executeUpdate() >0 ;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
