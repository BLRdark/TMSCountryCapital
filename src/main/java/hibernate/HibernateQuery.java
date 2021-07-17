package hibernate;

import entities.Capital;
import entities.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateQuery {
    private SessionFactory sessionFactory;

    public HibernateQuery(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Country> getCountryList(){
        Session session = sessionFactory.openSession();

        session.get(Country.class, 1);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Country.class);

        Root<Country> root = criteriaQuery.from(Country.class);

        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);

        List<Country> countryList = query.getResultList();

        session.close();

        return countryList;
    }

    public void pushCapital(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new Employee object
        Capital capital = new Capital();
        capital.setName(name);
        //Save the employee in database
        session.save(capital);

        //Commit the transaction
        session.getTransaction().commit();
    }

    public void pushCountry(String name, Capital capital){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new Employee object
        Country country = new Country();
        country.setName(name);
        if(capital == null) {
            capital = new Capital();
            capital.setName(" ");
        }
        country.setCapital(capital);
        //Save the employee in database
        session.save(country);

        //Commit the transaction
        session.getTransaction().commit();
        session.close();
    }
}
