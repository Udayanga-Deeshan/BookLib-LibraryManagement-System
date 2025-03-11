package entity;

import dto.BorrowDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.BorrowStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowEntity {
    private String orderId;

    private String  memberId;

    private String bookId;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    //private LocalDate returnDate;

    private BorrowStatus borrowStatus;

    private List<BorrowDetails> borrowedBooks;
}
