package config;

import com.google.inject.AbstractModule;
import repository.custom.*;
import repository.custom.impl.*;
import service.custom.*;
import service.custom.impl.*;

public class AppModule extends AbstractModule {

    @Override
    protected   void configure(){
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);
        bind(MemberService.class).to(MemberServiceImpl.class);
        bind(MemberDao.class).to(MemberDaoImpl.class);
        bind(LibrarianService.class).to(LibrarianServiceImpl.class);
        bind(LibrarianDao.class).to(LibrarianDaoImpl.class);
        bind(BorrowService.class).to(BorrowServiceImpl.class);
        bind(BorrowDao.class).to(BorrowDaoImpl.class);
        bind(BorrowDetailService.class).to(BorrowDetailServiceImpl.class);
        bind(BorrowDetailDao.class).to(BorrowDetailDaoImpl.class);

    }

}
