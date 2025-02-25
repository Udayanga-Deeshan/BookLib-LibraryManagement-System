package repository.custom.impl;

import db.DBConnection;
import dto.Book;
import entity.BookEntity;
import repository.CRUDRepository;
import repository.custom.BookDao;

import java.sql.*;
import java.util.ArrayList;
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
        String SQL = "SELECT * FROM book";
        List<BookEntity> bookEntityList = new ArrayList<>();
        try {
          Connection connection=  DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
               bookEntityList.add(new BookEntity(
                       resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5),
                       resultSet.getString(6)
               )) ;


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookEntityList;
    }

    @Override
    public BookEntity search(String s) {
        return null;
    }
}
