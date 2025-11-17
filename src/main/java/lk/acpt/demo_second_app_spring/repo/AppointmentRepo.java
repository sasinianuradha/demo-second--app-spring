package lk.acpt.demo_second_app_spring.repo;

import lk.acpt.demo_second_app_spring.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
}
