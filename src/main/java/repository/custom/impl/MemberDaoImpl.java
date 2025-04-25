package repository.custom.impl;

import db.DBConnection;
import entity.MemberEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.MemberDao;
import util.MembershipStatus;

import java.sql.*;
import java.util.ArrayList;
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
        String SQL="UPDATE member SET name=?, email=?,phone_number=?,membership_date=? WHERE member_id=? ";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,entity.getName());
            preparedStatement.setObject(2,entity.getEmail());
            preparedStatement.setObject(3,entity.getContactNumber());
            preparedStatement.setObject(4,entity.getMembershipDate());
            preparedStatement.setObject(5,entity.getMemberId());
            return preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE from member WHERE member_id='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MemberEntity> getAll() {
        List<MemberEntity> memberEntityList = new ArrayList<>();
        String SQL="SELECT * FROM member";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
               MemberEntity entity= new MemberEntity(
                     resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate()

                );
                memberEntityList.add(entity);
            }

            return  memberEntityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MemberEntity search(String id) {
         String SQL= "SELECT * FROM member WHERE member_id='"+id+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if(resultSet.next()){
                return new MemberEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate()

                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  null;


    }

    @Override
    public ObservableList<String> getMemberIds() {
        ObservableList<String> memberIdList = FXCollections.observableArrayList();
        List<MemberEntity> all = getAll();
        for(MemberEntity memberEntity:all){
            memberIdList.add(memberEntity.getMemberId());
        }

        return memberIdList;
    }
}
