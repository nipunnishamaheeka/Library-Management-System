package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

public interface AdminBO extends SuperBO {

    boolean saveAdmin(AdminDto dto) throws Exception;
    boolean updateAdmin(AdminDto dto) throws Exception;
    boolean deleteAdmin(String id) throws Exception;
    AdminDto searchAdmin(String id) throws Exception;

    ObservableList<AdminDto> getAllAdmins() throws Exception;

    Admin searchAdmin(String name, String password) throws Exception;


}
