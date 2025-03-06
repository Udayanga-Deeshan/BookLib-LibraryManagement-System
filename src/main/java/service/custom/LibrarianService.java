package service.custom;

import dto.Librarian;

public interface LibrarianService {

    boolean register(Librarian librarian);

    Librarian searchByEmail(String email);
}
