package controller;

import com.google.inject.Inject;
import dto.BorrowDetails;
import service.custom.BorrowDetailService;
import service.custom.impl.BorrowDetailServiceImpl;

import java.util.List;

public class BorrowDetailController {

    BorrowDetailService borrowDetailService = new BorrowDetailServiceImpl();

    public boolean addBorrowDetail(List<BorrowDetails> borrowDetails){
        for(BorrowDetails borrowDetail: borrowDetails){
            boolean isAddBorrowDetail = addBorrowDetail(borrowDetail);
            if(!isAddBorrowDetail){
                return  false;
            }
        }

        return  true;
    }
    
    public  boolean addBorrowDetail(BorrowDetails borrowDetail){
           return borrowDetailService.addBorrowDetails(borrowDetail);
    }
}
