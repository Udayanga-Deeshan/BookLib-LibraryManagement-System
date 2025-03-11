package repository.custom.impl;

import controller.BorrowDetailController;
import db.DBConnection;
import entity.BorrowEntity;
import repository.custom.BorrowDao;
import service.custom.BorrowDetailService;
import util.BorrowStatus;
import util.MembershipStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {

    @Override
    public boolean save(BorrowEntity entity) {
        String SQL="INSERT INTO borrowing_records (borrow_id,member_id,book_id,borrow_date,due_date,status) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getOrderId());
            preparedStatement.setObject(2,entity.getMemberId());
            preparedStatement.setObject(3,entity.getBookId());
            preparedStatement.setObject(4,entity.getBorrowDate());
            preparedStatement.setObject(5,entity.getDueDate());
            preparedStatement.setObject(6, (BorrowStatus.BORROWED).name());
            boolean isAddBorrowRecord = preparedStatement.executeUpdate() > 0;
            if(isAddBorrowRecord){
                boolean b = new BorrowDetailController().addBorrowDetail(entity.getBorrowedBooks());
                if (b){
                    return  true;
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
}
