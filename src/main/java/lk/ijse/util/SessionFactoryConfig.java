package lk.ijse.util;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.entity.Credentials;
import lk.ijse.entity.Transactions;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactoryConfig {


    private static SessionFactoryConfig factoryConfig;

    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Credentials.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(Transactions.class)
                .getMetadataBuilder().build();

        sessionFactory = metadata.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    @SneakyThrows
    public Session getSession(){
        return sessionFactory.openSession();
    }
}