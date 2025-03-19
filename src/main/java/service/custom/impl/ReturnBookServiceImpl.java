package service.custom.impl;

import com.google.inject.Inject;
import dto.BorrowDetails;
import dto.ReturnBook;
import entity.BorrowDetailsEntity;
import entity.ReturnBookEntity;
import org.modelmapper.ModelMapper;
import repository.custom.ReturnBookDao;
import service.custom.ReturnBookService;

import java.util.List;

public class ReturnBookServiceImpl implements ReturnBookService {
    @Inject
    ReturnBookDao returnBookDao;



}
