package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Credentials;

public interface CredentialsDAO extends CrudDAO<Credentials> {

    Credentials searchByUsername(String username);

}
