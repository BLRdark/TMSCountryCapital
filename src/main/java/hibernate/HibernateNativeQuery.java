package hibernate;

import entities.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateNativeQuery {
    private SessionFactory sessionFactory;

    public HibernateNativeQuery() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Country> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createNativeQuery("select * from country", Country.class).getResultList();

    }

    public void updateCountry(Country newCountry){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Country country = session.createNativeQuery("select * from country where name like \'" + newCountry.getName() +"\'", Country.class).getSingleResult();
        country.setCapital(newCountry.getCapital());
        session.save(country);
        session.persist(country);
        session.getTransaction().commit();

    }

}
