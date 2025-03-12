package service.custom.impl;

import dto.Book;
import dto.BorrowDetails;
import entity.BookEntity;
import entity.BorrowDetailsEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.BookDao;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {


    BookDao dao = new BookDaoImpl();
    @Override
    public boolean addBook(Book book) {
        System.out.println("service  "+book);
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);

       return dao.save(bookEntity);


    }

    @Override
    public boolean updateBook(Book book) {
        BookEntity bookEntity = new ModelMapper().map(book, BookEntity.class);
       return dao.update(bookEntity);
    }

    @Override
    public Book searchBook(String id) {
        BookEntity searchedBook = dao.search(id);
        if(searchedBook!=null){
            return new ModelMapper().map(searchedBook, Book.class);
        }

        return null;

    }

    @Override
    public List<Book> getAll() {
        List<BookEntity> bookEntityList = dao.getAll();
        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity bookEntity: bookEntityList){
            books.add(new ModelMapper().map(bookEntity,Book.class));
        }

        return  books;


    }

    @Override
    public boolean deleteBook(String id) {
        return false;
    }

    @Override
    public ObservableList<String> getBookIds() {
        return dao.getBookCodes();

    }

    public  boolean updateAvailability(List<BorrowDetails> borrowDetails){
        List<BorrowDetailsEntity> borrowDetailsEntities = new ArrayList<>();
        for(BorrowDetails borrowDetail: borrowDetails){
            BorrowDetailsEntity entity = new ModelMapper().map(borrowDetail, BorrowDetailsEntity.class);
            borrowDetailsEntities.add(entity);
            boolean isUpdateAvailability = updateAvailability(borrowDetail);
            if(!isUpdateAvailability){
                return  false;
            }
        }

        return  true;
    }

    public  boolean updateAvailability(BorrowDetails borrowDetail){

        BorrowDetailsEntity map = new ModelMapper().map(borrowDetail, BorrowDetailsEntity.class);

        return  dao.updateAvailability(map);
    }


}
