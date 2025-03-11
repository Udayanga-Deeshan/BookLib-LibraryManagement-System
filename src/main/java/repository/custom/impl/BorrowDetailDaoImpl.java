package repository.custom.impl;

import db.DBConnection;
import entity.BorrowDetailsEntity;
import repository.custom.BorrowDetailDao;
import service.custom.BorrowDetailService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowDetailDaoImpl implements BorrowDetailDao {

    @Override
    public boolean save(BorrowDetailsEntity entity) {
        String SQL="INSERT INTO borrowed_books (borrowId,bookId,borrowDate) VALUES(?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getBorrowID());
            preparedStatement.setObject(2,entity.getBookId());
            preparedStatement.setObject(3,entity.getBorrowedDate());
           return preparedStatement.executeUpdate()> 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(BorrowDetailsEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<BorrowDetailsEntity> getAll() {
        return List.of();
    }

    @Override
    public BorrowDetailsEntity search(String s) {
        return null;
    }
}
