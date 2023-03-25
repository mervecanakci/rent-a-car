package kodlama.io.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int id;
    @Column(name = "brand_name", unique = true)
    private String name;

    /*
    @OneToMany(mappedBy = "brand")
        private List<Car> cars;
     */

    }

