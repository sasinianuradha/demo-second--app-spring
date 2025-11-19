package lk.acpt.demo_second_app_spring.controller;

import lk.acpt.demo_second_app_spring.dto.PatientDto;
import lk.acpt.demo_second_app_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public PatientDto save(@RequestBody PatientDto dto) {
        return patientService.savePatient(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Integer id, @RequestBody PatientDto dto) {
              dto.setId(id);
PatientDto patientDto=patientService.updatePatient(dto);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String deleted=patientService.deletePatient(id);
        return  new ResponseEntity<>(deleted,HttpStatus.OK);
    }

    @GetMapping()
    public List<PatientDto> getAll() {
        return patientService.getAllPatients();
    }



}
