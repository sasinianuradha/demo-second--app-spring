package lk.acpt.demo_second_app_spring.service;

import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.dto.PatientDtoWithAppointment;

import java.util.List;


public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);

    PatientDto updatePatient(PatientDto patientDto);

    PatientDto deletePatient(Integer id);

    List<PatientDto> getAllPatients();

    PatientDto getPatientById(Integer id);

    PatientDtoWithAppointment savePatientWithAppointments(PatientDtoWithAppointment dto);
}
