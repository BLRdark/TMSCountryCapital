package controller;

import dnl.utils.text.table.TextTable;
import entities.Capital;
import entities.Country;
import hibernate.HibernateQuery;

import java.util.List;
import java.util.Scanner;

public class MainUtil {
    private HibernateQuery hq = new HibernateQuery();


    public void addCity() {
        System.out.println("Enter city name: ");
        String s = new Scanner(System.in).nextLine();
        hq.pushCapital(s);
    }

    public void addCountry() {
        System.out.println("Enter country name: ");
        String countryName = new Scanner(System.in).nextLine();
        System.out.println("Choose capital (0 for empty capital cell): ");
        printCapitalList();
        int f = new Scanner(System.in).nextInt();
        hq.pushCountry(countryName, getCapitalById(f));
    }

    public void printMixedList() {
        for (Country country : hq.getCountryList()) {
            if (country.getCapital() == null) System.out.println(country.getId() + " " + country.getName());
            else System.out.println(country);
        }
    }

    public void printCountryList() {
        for (Country country : hq.getCountryList()) {
            System.out.println(country.getId() + " " + country.getName());
        }
    }

    public void printCapitalList() {
        for (Capital capital : hq.getCapitalList()) {
            System.out.println(capital.getId() + " " + capital.getName());
        }
    }

    public void findCapitalById(Integer id) {
        Country country = getCountryById(id);
        System.out.println("The capital of " + country.getName() + " is " + country.getCapital().getName() + "!");
    }

    private Capital getCapitalById(Integer id) {
        for (Capital capital : hq.getCapitalList()) {
            if (capital.getId() == id) {
                return capital;
            }
        }
        return null;
    }

    private Country getCountryById(Integer id) {
        for (Country country : hq.getCountryList()) {
            if (country.getCapital().getId() == id) {
                return country;
            }
        }
        return null;
    }


}
