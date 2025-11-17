package lk.acpt.demo_second_app_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String doctorName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
