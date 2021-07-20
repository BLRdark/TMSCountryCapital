package controller;
import entities.Capital;
import entities.Country;
import hibernate.HibernateNativeQuery;

public class TestNativeQueryClass {
    public static void main(String[] args) {
        {
            Country country = new Country();
            country.setName("test_name");
        }
        for (Country country: new HibernateNativeQuery().getList()
        ) {
            System.out.println(country);
        }
    }
}
