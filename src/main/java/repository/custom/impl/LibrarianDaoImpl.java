package repository.custom.impl;

import db.DBConnection;
import entity.LibrarianEntity;
import repository.custom.LibrarianDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LibrarianDaoImpl implements LibrarianDao {
    @Override
    public boolean save(LibrarianEntity entity) {
        String SQL="INSERT INTO librarian (username,email,password) VALUES(?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getUsername());
            preparedStatement.setObject(2,entity.getEmail());
            preparedStatement.setObject(3,entity.getPassword());
            return preparedStatement.executeUpdate()> 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(LibrarianEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<LibrarianEntity> getAll() {
        return List.of();
    }

    @Override
    public LibrarianEntity search(String s) {
        return null;
    }
}
