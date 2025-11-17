package lk.acpt.demo_second_app_spring.repo;

import lk.acpt.demo_second_app_spring.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
