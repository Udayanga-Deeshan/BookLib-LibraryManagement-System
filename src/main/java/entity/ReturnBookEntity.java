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
public class ReturnBookEntity {

    private String borrowId;

    private  String bookId;

    private String memberId;

    private LocalDate returnDate;

}
