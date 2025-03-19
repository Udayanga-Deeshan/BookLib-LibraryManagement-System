package repository.custom.impl;

import entity.BorrowDetailsEntity;
import entity.ReturnBookEntity;
import repository.custom.ReturnBookDao;

import java.util.List;

public class ReturnBookDaoImpl implements ReturnBookDao {

    @Override
    public boolean save(ReturnBookEntity entity) {
        return false;
    }

    @Override
    public boolean update(ReturnBookEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<ReturnBookEntity> getAll() {
        return List.of();
    }

    @Override
    public ReturnBookEntity search(String s) {
        return null;
    }


}
