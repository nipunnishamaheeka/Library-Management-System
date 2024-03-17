package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Credentials;

import java.util.List;

public interface CredentialsDAO extends CrudDAO<Credentials> {

    Credentials searchByUsername(String username);

    List<Credentials> loadAll(boolean isAdmin);

}
