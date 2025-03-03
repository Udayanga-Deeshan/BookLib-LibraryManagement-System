package repository.custom.impl;

import db.DBConnection;
import entity.MemberEntity;
import repository.custom.MemberDao;
import util.MembershipStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean save(MemberEntity entity) {
        String SQL="INSERT INTO member VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getMemberId());
            preparedStatement.setObject(2,entity.getName());
            preparedStatement.setObject(3,entity.getEmail());
            preparedStatement.setObject(4,entity.getContactNumber());
            preparedStatement.setObject(5,entity.getMembershipDate());
            preparedStatement.setObject(6, MembershipStatus.ACTIVE.name());
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(MemberEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<MemberEntity> getAll() {
        return List.of();
    }

    @Override
    public MemberEntity search(String s) {
        return null;
    }
}
