package lk.acpt.demo_second_app_spring.repo;

import lk.acpt.demo_second_app_spring.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByPatientId(Integer patientId);
}
