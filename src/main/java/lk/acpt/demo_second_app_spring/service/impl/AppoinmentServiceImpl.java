package lk.acpt.demo_second_app_spring.service.impl;

import lk.acpt.demo_second_app_spring.dto.AppointmentDto;
import lk.acpt.demo_second_app_spring.entity.Appointment;
import lk.acpt.demo_second_app_spring.entity.Patient;
import lk.acpt.demo_second_app_spring.repo.AppointmentRepo;
import lk.acpt.demo_second_app_spring.repo.PatientRepo;
import lk.acpt.demo_second_app_spring.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class AppoinmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public AppointmentDto save(AppointmentDto dto) {
        Optional<Patient> patient = patientRepo.findById(dto.getPatient_id());
        if (patient.isEmpty()) return null;

        Appointment a = new Appointment(
                dto.getId(),
                dto.getDoctorName(),
                dto.getDescription(),
                dto.getDate(),
                dto.getTime(),
                patient.get()
        );

        Appointment saved = appointmentRepo.save(a);

        return new AppointmentDto(saved.getId(), saved.getDoctorName(), saved.getDescription(),saved.getDate(), saved.getTime(),saved.getPatient().getId());

    }


    @Override
    public AppointmentDto delete(Integer id) {
        Optional<Appointment> byId = appointmentRepo.findById(id);

        if (byId.isPresent()) {
            appointmentRepo.deleteById(id);
            Appointment a = byId.get();
            return new AppointmentDto(a.getId(), a.getDoctorName(), a.getDescription(),a.getDate(),a.getTime(), a.getPatient().getId());
        }
        return null;

    }

    @Override
    public List<AppointmentDto> getByPatientId(Integer patientId) {
        List<Appointment> list = appointmentRepo.findByPatientId(patientId);
        List<AppointmentDto> dtos = new ArrayList<>();

        for (Appointment a : list) {
            dtos.add(new AppointmentDto(a.getId(), a.getDoctorName(), a.getDescription(),a.getDate(),a.getTime(), patientId));
        }
        return dtos;


    }


}
