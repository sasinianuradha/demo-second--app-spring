package lk.acpt.demo_second_app_spring.service;

import lk.acpt.demo_second_app_spring.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto save(AppointmentDto dto);

    AppointmentDto delete(Integer id);
    List<AppointmentDto> getByPatientId(Integer patientId);
}
