package lk.acpt.demo_second_app_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Integer id;
    private String name;
    private int age;
    private String address;
    private double payment;

}
