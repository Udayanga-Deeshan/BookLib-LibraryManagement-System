package service.custom.impl;

import com.google.inject.Inject;
import dto.BorrowDetails;
import entity.BorrowDetailsEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDetailDao;
import repository.custom.impl.BorrowDetailDaoImpl;
import service.custom.BorrowDetailService;

public class BorrowDetailServiceImpl implements BorrowDetailService {
    @Inject
    BorrowDetailDao dao = new BorrowDetailDaoImpl() ;
    @Override
    public boolean addBorrowDetails(BorrowDetails borrowDetails) {
        BorrowDetailsEntity map = new ModelMapper().map(borrowDetails, BorrowDetailsEntity.class);
        return dao.save(map);
    }
}
