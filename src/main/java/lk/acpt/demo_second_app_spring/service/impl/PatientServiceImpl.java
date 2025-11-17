package lk.acpt.demo_second_app_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.dto.PatientDtoWithAppointment;
import lk.acpt.demo_second_app_spring.entity.Appointment;
import lk.acpt.demo_second_app_spring.entity.Patient;
import lk.acpt.demo_second_app_spring.repo.AppointmentRepo;
import lk.acpt.demo_second_app_spring.repo.PatientRepo;
import lk.acpt.demo_second_app_spring.service.PatientService;
import lk.acpt.demo_second_app_spring.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private PatientRepo patientRepo;

    @Autowired
   public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public PatientDto savePatient(PatientDto patientDto) {

        Patient patient = new Patient(
                patientDto.getId(),
                patientDto.getName(),
                patientDto.getAge(),
                patientDto.getAddress(),
                patientDto.getPayment(),
                null
        );

        Patient saved = patientRepo.save(patient);
        return DtoMapper.toPatientDto(saved);
    }

    @Override
    public PatientDto updatePatient(PatientDto dto) {

        Optional<Patient> byId = patientRepo.findById(dto.getId());

        if (byId.isPresent()) {
            Patient p = byId.get();

            p.setName(dto.getName());
            p.setAge(dto.getAge());
            p.setAddress(dto.getAddress());
            p.setPayment(dto.getPayment());

            Patient saved = patientRepo.save(p);
            return DtoMapper.toPatientDto(saved);
        }
        return null;
    }

    @Override
    public PatientDto deletePatient(Integer id) {

        Optional<Patient> byId = patientRepo.findById(id);

        if (byId.isPresent()) {
            patientRepo.deleteById(id);
            return DtoMapper.toPatientDto(byId.get());
        }
        return null;
    }

    @Override
    public List<PatientDto> getAllPatients() {

        List<Patient> all = patientRepo.findAll();
        List<PatientDto> dtos = new ArrayList<>();

        for (Patient p : all) {
            dtos.add(DtoMapper.toPatientDto(p));
        }
        return dtos;
    }

    @Override
    public PatientDto getPatientById(Integer id) {
        return patientRepo.findById(id)
                .map(DtoMapper::toPatientDto)
                .orElse(null);
    }

    @Override
    public PatientDtoWithAppointment savePatientWithAppointments(PatientDtoWithAppointment dto) {

        Patient patient = new Patient(
                dto.getId(),
                dto.getName(),
                dto.getAge(),
                dto.getAddress(),
                dto.getPayment(),
                new ArrayList<>()
        );

        List<Appointment> appointments = new ArrayList<>();

        dto.getAppointment().forEach(a -> {
            appointments.add(new Appointment(
                    a.getId(),
                    a.getDoctorName(),
                    a.getDescription(),
                    patient
            ));
        });

        patient.setAppointments(appointments);

        Patient saved = patientRepo.save(patient);

        return DtoMapper.toPatientWithAppointments(saved);
    }

}
