package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.MembershipStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MemberEntity {

    private String memberId;

    private String name;

    private String email;

    private  String contactNumber;

    private LocalDate membershipDate;




}
