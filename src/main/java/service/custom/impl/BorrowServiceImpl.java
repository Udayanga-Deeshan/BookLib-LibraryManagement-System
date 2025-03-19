package service.custom.impl;

import com.google.inject.Inject;
import dto.Borrow;
import entity.BorrowEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDao;
import service.custom.BorrowService;

public class BorrowServiceImpl implements BorrowService {
    @Inject
    BorrowDao dao;
    @Override
    public boolean borrowBooks(Borrow borrow) {

       return dao.save(new ModelMapper().map(borrow, BorrowEntity.class));

    }

    @Override
    public Borrow findBorrowedBooksById(String id) {
        BorrowEntity borrowedBooksById = dao.findBorrowedBooksById(id);
        return  new ModelMapper().map(borrowedBooksById,Borrow.class);
    }
}
