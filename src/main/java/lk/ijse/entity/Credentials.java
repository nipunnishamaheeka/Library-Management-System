package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.CredentialsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int Uid;

    private String name;

    private String email;

    private String password;
    private String imageSrc;
    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Transactions> transactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Branch> branches = new ArrayList<>();

    public CredentialsDto toDTO(){
        return new CredentialsDto(Uid,name,email,password,imageSrc,isAdmin);
    }


}
