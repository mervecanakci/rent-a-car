package kodlama.io.rentacar.entities.concretes;

import jakarta.persistence.*;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {

    @Id // Primary Key -> PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private State state; // 1- Available, 2- Rented, 3- Maintance
    @ManyToOne
    // @JsonManagedReference
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
  //  @ManyToOne
   // @JoinColumn(name = "color_id")
   // private Color color;

}
