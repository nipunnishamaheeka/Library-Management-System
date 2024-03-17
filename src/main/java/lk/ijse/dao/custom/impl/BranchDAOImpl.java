package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.entity.Branch;
import org.hibernate.Session;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Branch dto) throws Exception {
        int value = (int) session.save(dto);
        return value > 0 ?true : false;
    }

    @Override
    public boolean update(int id, Branch dto) throws Exception {
//        Session updateSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction updateTransaction = updateSession.beginTransaction();
//        Branch branch = updateSession.get(Branch.class, id);
//        if (branch != null) {
//            branch.setEmail(dto.getEmail());
//            branch.setLocation(dto.getLocation());
//            branch.setName(dto.getName());
//            updateSession.merge(branch);
//        } else {
//            updateTransaction.commit();
//            updateSession.close();
//            return false;
//        }
//        updateTransaction.commit();
//        updateSession.close();
//        return true;
        try {
            session.update(dto);
            return true;
        } catch (Exception e) {
            return false;
        }
     }

    @Override
    public boolean delete(int id) throws Exception {

//        Session deleteSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction deleteTransaction = deleteSession.beginTransaction();
//        Branch branch = deleteSession.get(Branch.class, id);
//        if (branch != null){
//            deleteSession.remove(branch);
//            deleteTransaction.commit();
//            deleteSession.close();
//            return true;
//        } else {
//            return false;
//        }
        session.delete(session.get(Branch.class,id));

        return false;
    }

    @Override
    public Branch search(int id) throws Exception {
//        Session searchSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction searchTransaction = searchSession.beginTransaction();
//        Branch branch = searchSession.get(Branch.class,id);
//        searchTransaction.commit();
//        searchSession.close();
//        return branch;
        return session.get(Branch.class,id);
    }

    @Override
    public List<Branch> loadAll() throws Exception {
        return session.createQuery("FROM Branch").list();

    }
}
