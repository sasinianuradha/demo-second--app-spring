package lk.acpt.demo_second_app_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.entity.Patient;
import lk.acpt.demo_second_app_spring.repo.AppointmentRepo;
import lk.acpt.demo_second_app_spring.repo.PatientRepo;
import lk.acpt.demo_second_app_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public PatientDto savePatient(PatientDto patientDto, MultipartFile photo) {
        String photoPath = null;

        try {
            if (photo != null && !photo.isEmpty()) {

                String folder = "C:\\Users\\LOQ\\OneDrive\\Documents\\AFSD\\demo-second\\demo-second-app-spring-backend-file";  // <-- You can change this
                File directory = new File(folder);
                if (!directory.exists()) directory.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
                File file = new File(folder + File.separator + fileName);
//                photoPath = folder + File.separator + fileName;


                // String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
                // File file = new File(folder + fileName);

                photo.transferTo(file);
                photoPath = fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Patient patient = new Patient(
                patientDto.getId(),
                patientDto.getName(),
                patientDto.getAge(),
                patientDto.getAddress(),
                patientDto.getPhoneNumber(),
                photoPath,
                null
        );

        Patient saved = patientRepo.save(patient);
        return new PatientDto(saved.getId(), saved.getName(), saved.getAge(), saved.getAddress(), saved.getPhoneNumber(), saved.getPhotoUrl());
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
            return new PatientDto(saved.getId(), saved.getName(), saved.getAge(), saved.getAddress(), saved.getPhoneNumber());
        }
        return null;
    }

    @Override
    public String deletePatient(Integer id) {

        Optional<Patient> byId = patientRepo.findById(id);

        if (byId.isPresent()) {
            patientRepo.deleteById(id);
            //Patient saved =  byId.get();
            return new String("Patient has been deleted");

        }
        return String.format("Patient can't be found: " + id);
    }

    @Override
    public List<PatientDto> getAllPatients() {

        List<Patient> all = patientRepo.findAll();
        List<PatientDto> dtos = new ArrayList<>();

        for (Patient p : all) {
            dtos.add(new PatientDto(p.getId(), p.getName(), p.getAge(), p.getAddress(), p.getPhoneNumber(), p.getPhotoUrl()));
        }
        return dtos;
    }


}
