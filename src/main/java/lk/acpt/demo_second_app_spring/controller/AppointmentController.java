package lk.acpt.demo_second_app_spring.controller;

import lk.acpt.demo_second_app_spring.dto.AppointmentDto;
import lk.acpt.demo_second_app_spring.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public AppointmentDto save(@RequestBody AppointmentDto dto) {
        return appointmentService.save(dto);
    }



    @DeleteMapping("/delete/{id}")
    public AppointmentDto delete(@PathVariable Integer id) {
        return appointmentService.delete(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDto> getByPatient(@PathVariable Integer patientId) {
        return appointmentService.getByPatientId(patientId);
    }

}
