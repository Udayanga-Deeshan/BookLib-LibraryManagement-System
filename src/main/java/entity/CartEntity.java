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
public class CartEntity {

    private  String bookCode;

    private String title;

    private LocalDate borrowDate;

    private LocalDate  dueDate;


}
