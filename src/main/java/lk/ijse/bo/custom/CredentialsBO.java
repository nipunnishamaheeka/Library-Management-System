package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.entity.Credentials;

public interface CredentialsBO extends SuperBO{

    boolean save(CredentialsDto dto) throws Exception;


    ObservableList<CredentialsDto> getAll() throws Exception;

    CredentialsDto searchUser(String text);
}
