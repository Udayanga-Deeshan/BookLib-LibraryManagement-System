package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {

    private  String bookCode;

    private String title;

    private LocalDate borrowDate;

    private LocalDate  returnDate;


}
