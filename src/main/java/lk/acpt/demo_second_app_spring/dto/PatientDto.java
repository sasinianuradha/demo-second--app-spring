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
    private String phoneNumber;
    private String photoUrl;

    public PatientDto(Integer id, String name, int age, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
