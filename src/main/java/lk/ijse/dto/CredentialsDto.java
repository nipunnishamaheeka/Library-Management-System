package lk.ijse.dto;

import lk.ijse.entity.Credentials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialsDto {
    private int Uid;
    private String name;
    private String email;
    private String password;
    private String imageSrc;
    private boolean isAdmin;

    public Credentials toEntity() {
        Credentials credentials = new Credentials();
        credentials.setName(this.name);
        credentials.setEmail(this.email);
        credentials.setPassword(this.password);
        credentials.setImageSrc(this.imageSrc);
        credentials.setAdmin(this.isAdmin);
        return credentials;
    }
}
