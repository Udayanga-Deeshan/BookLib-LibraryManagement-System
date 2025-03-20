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
public class ReturnBook {

    private String borrowId;

    private String memberId;

    private  String bookId;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private BorrowStatus borrowStatus;

}
