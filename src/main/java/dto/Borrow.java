package dto;

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
public class Borrow {
    private String orderId;

    private String  memberId;



    private LocalDate borrowDate;

    private LocalDate dueDate;

  //  private LocalDate returnDate;

    private BorrowStatus borrowStatus;

    private List<BorrowDetails> borrowedBooks;
}
