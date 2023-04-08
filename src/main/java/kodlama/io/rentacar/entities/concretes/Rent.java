package kodlama.io.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startingDate;//
    private LocalDateTime endDate;
    private double dailyPrice;
    private double totalPrice;//
    private int rentedForDays; //
    private boolean isCompleted;
    private double startingKilometer;
    private double returnKilometer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


}
