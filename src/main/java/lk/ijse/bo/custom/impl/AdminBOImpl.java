package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

public class AdminBOImpl implements AdminBO {

    private final AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public boolean saveAdmin(AdminDto dto) throws Exception {
        return adminDAO.save(new Admin(
                dto.getName(),
                dto.getPassword()

        ));

    }

    @Override
    public boolean updateAdmin(AdminDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteAdmin(String id) throws Exception {
        return false;
    }

    @Override
    public AdminDto searchAdmin(String id) throws Exception {
        return null;
    }

    @Override
    public Admin searchAdmin(String name,String password) throws Exception {
        Admin admin = adminDAO.searchAdmin(name, password);
        if (admin != null) {
            return new Admin(
                    admin.getName(),
                    admin.getPassword()
            );
        }
            return null;
        }


    @Override
    public ObservableList<AdminDto> getAllAdmins() throws Exception {
        return null;
    }

}
