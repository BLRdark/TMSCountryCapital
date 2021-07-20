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

    public HibernateQuery() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Country> getCountryList() {
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

    public List<Capital> getCapitalList() {
        Session session = sessionFactory.openSession();

        session.get(Capital.class, 1);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Capital.class);

        Root<Country> root = criteriaQuery.from(Capital.class);

        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);

        List<Capital> capitalList = query.getResultList();

        session.close();

        return capitalList;
    }


    public void pushCapital(Capital capital) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(capital);

        session.getTransaction().commit();
    }

    public void pushCountry(Country country) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(country);

        session.getTransaction().commit();
        session.close();
    }

    public void updateObj(Object obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
    }

    public void removeObj(Object obj) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        if (obj instanceof Capital) {
            for (Country country : getCountryList()) {
                if (country.getCapital() == null) {
                    continue;
                } else if (country.getCapital().getName().equals(((Capital) obj).getName())) {
                    country.setCapital(null);
                   session.update(country);
                }
            }
        }

        session.remove(obj);
        session.getTransaction().commit();
    }
}
