package lk.acpt.demo_second_app_spring.service;

import lk.acpt.demo_second_app_spring.dto.PatientDto;

import java.util.List;


public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);

    PatientDto updatePatient(PatientDto patientDto);

    String deletePatient(Integer id);

    List<PatientDto> getAllPatients();

}
