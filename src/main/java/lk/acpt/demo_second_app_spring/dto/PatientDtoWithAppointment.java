package lk.acpt.demo_second_app_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDtoWithAppointment {
    private Integer id;
    private String name;
    private int age;
    private String address;
    private double payment;
    List<AppointmentDto> appointment;
}
