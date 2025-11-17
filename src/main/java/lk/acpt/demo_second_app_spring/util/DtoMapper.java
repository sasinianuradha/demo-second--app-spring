package lk.acpt.demo_second_app_spring.util;

import lk.acpt.demo_second_app_spring.dto.AppointmentDto;
import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.dto.PatientDtoWithAppointment;
import lk.acpt.demo_second_app_spring.entity.Appointment;
import lk.acpt.demo_second_app_spring.entity.Patient;

import java.util.stream.Collectors;

public class DtoMapper {
    public static PatientDto toPatientDto(Patient p) {
        return new PatientDto(
                p.getId(),
                p.getName(),
                p.getAge(),
                p.getAddress(),
                p.getPayment()
        );
    }

    public static AppointmentDto toAppointmentDto(Appointment a) {
        return new AppointmentDto(
                a.getId(),
                a.getDoctorName(),
                a.getDescription()
        );
    }

    public static PatientDtoWithAppointment toPatientWithAppointments(Patient p) {
        return new PatientDtoWithAppointment(
                p.getId(),
                p.getName(),
                p.getAge(),
                p.getAddress(),
                p.getPayment(),
                p.getAppointments()
                        .stream()
                        .map(DtoMapper::toAppointmentDto)
                        .collect(Collectors.toList())
        );
    }
}
