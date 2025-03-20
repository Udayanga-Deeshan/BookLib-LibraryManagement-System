package repository.custom.impl;

import db.DBConnection;
import entity.BorrowDetailsEntity;
import repository.custom.BorrowDetailDao;
import service.custom.BorrowDetailService;
import util.BorrowStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String query="UPDATE borrowed_books SET  returnDate =? ,status=? WHERE borrowId =? AND bookID =?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,entity.getReturnDate());
            preparedStatement.setObject(2, BorrowStatus.RETURNED.name());
            preparedStatement.setString(3,entity.getBorrowID());
            preparedStatement.setString(4,entity.getBookId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

    @Override
    public boolean areAllBooksRetured(String borrowId) {
        String query = "SELECT COUNT(*) AS pending_books FROM borrowed_books WHERE borrowId = ? AND returnDate IS NULL";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,borrowId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() && resultSet.getInt("pending_books") == 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
