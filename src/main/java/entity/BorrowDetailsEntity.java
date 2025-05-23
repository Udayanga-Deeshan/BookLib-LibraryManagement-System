package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowDetailsEntity {

    private String borrowID;

    private String bookId;

    private LocalDate borrowedDate;

    private  LocalDate returnDate;
}
