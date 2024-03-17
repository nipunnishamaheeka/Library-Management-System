package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.tm.UserManagementTm;

public interface CredentialsBO extends SuperBO{

    boolean save(CredentialsDto dto) throws Exception;
    boolean update(CredentialsDto credentialsDto) throws Exception;

    ObservableList<UserManagementTm> getAll(boolean isAdmin) throws Exception;

    CredentialsDto searchUser(String text);
    void deleteUser(int userId);
}
