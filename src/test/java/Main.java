import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.util.List;

public class Main {

    private static final BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    public static void main(String[] args) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            bookDAO.setSession(session);
            List<Book> books = bookDAO.loadAll();
            System.out.println(books);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
