package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "Branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")

    private int id;
    private String name;
    private String location;
    private String email;

    @ManyToOne
    private Credentials user;

    public Branch(String name, String location, String email) {
        this.name = name;
        this.location = location;
        this.email = email;
    }
}
