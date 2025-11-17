package lk.acpt.demo_second_app_spring.controller;

import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.dto.PatientDtoWithAppointment;
import lk.acpt.demo_second_app_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public PatientDto save(@RequestBody PatientDto dto) {
        return patientService.savePatient(dto);
    }

    @PutMapping("/update")
    public PatientDto update(@RequestBody PatientDto dto) {
        return patientService.updatePatient(dto);
    }

    @DeleteMapping("/delete/{id}")
    public PatientDto delete(@PathVariable Integer id) {
        return patientService.deletePatient(id);
    }

    @GetMapping("/all")
    public List<PatientDto> getAll() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable Integer id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/save-with-appointments")
    public PatientDtoWithAppointment saveWithAppointments(@RequestBody PatientDtoWithAppointment dto) {
        return patientService.savePatientWithAppointments(dto);
    }
}
