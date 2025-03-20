package service.custom.impl;

import com.google.inject.Inject;
import db.DBConnection;
import dto.BorrowDetails;
import dto.ReturnBook;
import entity.BorrowDetailsEntity;
import entity.ReturnBookEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BookDao;
import repository.custom.BorrowDao;
import repository.custom.BorrowDetailDao;
import repository.custom.ReturnBookDao;
import repository.custom.impl.BorrowDetailDaoImpl;
import service.custom.BookService;
import service.custom.ReturnBookService;
import util.BookAvailabilityStatus;
import util.BorrowStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReturnBookServiceImpl implements ReturnBookService {
    @Inject
    ReturnBookDao returnBookDao;
    
    @Inject
    BookDao bookDao;

    @Inject
    BorrowDetailDao borrowDetailDao;

    @Inject
    BorrowDao borrowDao;


    @Override
    public boolean returnBook(ReturnBook returnBook) {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            BorrowDetailsEntity borrowDetailsEntity = new BorrowDetailsEntity(
                    returnBook.getBorrowId(),
                    returnBook.getBookId(),
                    returnBook.getBorrowDate(),
                    returnBook.getReturnDate()
            );

            boolean updatedBorrowedBook = new BorrowDetailDaoImpl().update(borrowDetailsEntity);
            if (!updatedBorrowedBook) {
                connection.rollback();
                return false;
            }

            boolean isUpdateAvailability = bookDao.updateAvailability(returnBook.getBookId(), BookAvailabilityStatus.AVAILABLE.name());
            if (!isUpdateAvailability) {
                connection.rollback();
                return false;
            }

            boolean areAllBooksRetured = borrowDetailDao.areAllBooksRetured(returnBook.getBorrowId());

            if (areAllBooksRetured) {
                boolean isUpdadeBorrowingRecord = borrowDao.updateStatusToReturned(returnBook.getBorrowId());
                if (!isUpdadeBorrowingRecord) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
