package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.entity.Credentials;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BranchBOImpl implements BranchBO {
    private Session session;

    private Transaction transaction;
    private final BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
private final CredentialsDAO credentialsDAO = (CredentialsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREDENTIALS);

    @Override
    public List<BranchDto> getAll() throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        List<Branch> all = branchDAO.loadAll();
        List<BranchDto> branchDtos = all.stream().map(branch -> new BranchDto(
                branch.getId(),
                branch.getName(),
                branch.getLocation(),
                branch.getEmail()
        )).collect(Collectors.toList());
        session.close();
        return branchDtos;
    }

    @Override
    public boolean save(BranchDto branchDto) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        credentialsDAO.setSession(session);
        Credentials user = credentialsDAO.search(LoginFormController.userID);
        boolean save = branchDAO.save(new Branch(
                branchDto.getId(),
                branchDto.getName(),
                branchDto.getLocation(),
                branchDto.getEmail(),
                user
        ));
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();

        return save;
    }

    @Override
    public boolean update(BranchDto branchDto) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        transaction = session.beginTransaction();
        Branch branch = branchDAO.search(branchDto.getId());
        branch.setName(branchDto.getName());
        branch.setLocation(branchDto.getLocation());
        branch.setEmail(branchDto.getEmail());

        boolean update = branchDAO.update(branch.getId(), branch);

        transaction.commit();
        session.close();

        return update;
    }

    @Override
    public void delete(int id) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        branchDAO.setSession(session);
        branchDAO.delete(id);
        transaction = session.beginTransaction();
        transaction.commit();
        session.close();

    }
}
