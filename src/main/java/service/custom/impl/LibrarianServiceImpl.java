package service.custom.impl;

import com.google.inject.Inject;
import dto.Librarian;
import entity.LibrarianEntity;
import org.jasypt.util.text.BasicTextEncryptor;
import org.modelmapper.ModelMapper;
import repository.custom.LibrarianDao;
import service.custom.LibrarianService;

public class LibrarianServiceImpl implements LibrarianService {
    @Inject
    LibrarianDao dao;

    String key ="#345891AG";
    BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

    @Override
    public boolean register(Librarian librarian) {

        basicTextEncryptor.setPassword(key);
       String encryptPassword = basicTextEncryptor.encrypt(librarian.getPassword());
        librarian.setPassword(encryptPassword);
        System.out.println(librarian);
        return dao.save(new ModelMapper().map(librarian, LibrarianEntity.class));



    }

    @Override
    public Librarian searchByEmail(String email) {
        return null;
    }
}
