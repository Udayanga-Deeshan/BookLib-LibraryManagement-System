package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnBook {

    private String borrowId;

    private  String bookId;

    private String memberId;

    private LocalDate returnDate;



}
