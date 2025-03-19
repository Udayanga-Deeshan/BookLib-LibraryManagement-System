package repository.custom.impl;

import controller.BorrowDetailController;
import db.DBConnection;
import dto.BorrowDetails;
import entity.BorrowEntity;
import repository.custom.BorrowDao;
import service.custom.BorrowDetailService;
import service.custom.impl.BookServiceImpl;
import util.BorrowStatus;
import util.MembershipStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {

    @Override
    public boolean save(BorrowEntity entity) {
        String SQL="INSERT INTO borrowing_records (borrow_id,member_id,borrow_date,due_date,status) VALUES(?,?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getOrderId());
            preparedStatement.setObject(2,entity.getMemberId());
            preparedStatement.setObject(3,entity.getBorrowDate());
            preparedStatement.setObject(4,entity.getDueDate());
            preparedStatement.setObject(5, (BorrowStatus.BORROWED).name());
            boolean isAddBorrowRecord = preparedStatement.executeUpdate() > 0;
            if(isAddBorrowRecord){
                boolean isAddBorrowDetails = new BorrowDetailController().addBorrowDetail(entity.getBorrowedBooks());
                if (isAddBorrowDetails){
                    boolean isUpdateAvailabilty = new BookServiceImpl().updateAvailability(entity.getBorrowedBooks());
                    if(isUpdateAvailabilty){
                        return  true;
                    }
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  false;
    }

    @Override
    public boolean update(BorrowEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<BorrowEntity> getAll() {
        return List.of();
    }

    @Override
    public BorrowEntity search(String s) {
        return null;
    }

    @Override
    public BorrowEntity findBorrowedBooksById(String borrowId) {

        String query = "SELECT br.member_id, br.borrow_date, br.due_date, br.status, " +
                "bb.bookId, bb.borrowDate, bb.returnDate " +
                "FROM borrowing_records br " +
                "INNER JOIN borrowed_books bb ON br.borrow_id = bb.borrowId " +
                "WHERE br.borrow_id = ?";

        BorrowEntity borrow = null;
        List<BorrowDetails> borrowDetailsList = new ArrayList<>();

        try (PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, borrowId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (borrow == null) { // Set main Borrow object only once
                    borrow = new BorrowEntity();
                    borrow.setOrderId(borrowId);
                    borrow.setMemberId(resultSet.getString("member_id"));
                    borrow.setBorrowDate(resultSet.getDate("borrow_date").toLocalDate());
                    borrow.setDueDate(resultSet.getDate("due_date").toLocalDate());
                    borrow.setBorrowStatus(BorrowStatus.valueOf(resultSet.getString("status")));
                }

                BorrowDetails borrowDetails = new BorrowDetails(
                        borrowId,
                        resultSet.getString("bookId"),
                        resultSet.getDate("borrowDate").toLocalDate(),
                        resultSet.getDate("returnDate") != null ? resultSet.getDate("returnDate").toLocalDate() : null
                );

                borrowDetailsList.add(borrowDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (borrow != null) {
            borrow.setBorrowedBooks(borrowDetailsList);
        }

        return borrow;



    }
}
