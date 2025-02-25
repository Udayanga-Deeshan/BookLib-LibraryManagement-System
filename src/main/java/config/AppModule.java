package config;

import com.google.inject.AbstractModule;
import repository.custom.BookDao;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;
import service.custom.impl.BookServiceImpl;

public class AppModule extends AbstractModule {

    @Override
    public  void configure(){
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);
    }

}
