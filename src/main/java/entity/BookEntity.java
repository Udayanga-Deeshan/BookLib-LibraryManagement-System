package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookEntity {

    private String BookId;

    private String ISBN;

    private String title;

    private  String author;

    private String genre;

    private  String availability;


}
