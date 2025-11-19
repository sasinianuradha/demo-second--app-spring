package lk.acpt.demo_second_app_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.entity.Patient;
import lk.acpt.demo_second_app_spring.repo.AppointmentRepo;
import lk.acpt.demo_second_app_spring.repo.PatientRepo;
import lk.acpt.demo_second_app_spring.service.PatientService;
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
                patientDto.getPhoneNumber(),
                null
        );

        Patient saved = patientRepo.save(patient);
        return new PatientDto(saved.getId(), saved.getName(),saved.getAge(),saved.getAddress(),saved.getPhoneNumber());
    }

    @Override
    public PatientDto updatePatient(PatientDto dto) {

        Optional<Patient> byId = patientRepo.findById(dto.getId());

        if (byId.isPresent()) {
            Patient p = byId.get();

            p.setName(dto.getName());
            p.setAge(dto.getAge());
            p.setAddress(dto.getAddress());
            p.setPhoneNumber(dto.getPhoneNumber());

            Patient saved = patientRepo.save(p);
            return new PatientDto(saved.getId(), saved.getName(),saved.getAge(),saved.getAddress(),saved.getPhoneNumber());
        }
        return null;
    }

    @Override
    public String deletePatient(Integer id) {

        Optional<Patient> byId = patientRepo.findById(id);

        if (byId.isPresent()) {
            patientRepo.deleteById(id);
            Patient saved =  byId.get();
            return new String("Patient has been deleted");

        }
        return String.format("Patient can't be found: ",id);
    }

    @Override
    public List<PatientDto> getAllPatients() {

        List<Patient> all = patientRepo.findAll();
        List<PatientDto> dtos = new ArrayList<>();

        for (Patient p : all) {
            dtos.add(new PatientDto(p.getId(), p.getName(), p.getAge(), p.getAddress(), p.getPhoneNumber()));
        }
        return dtos;
    }




}
