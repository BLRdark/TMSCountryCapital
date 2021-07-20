package controller;

import entities.Capital;
import entities.Country;
import hibernate.HibernateQuery;

import java.util.Scanner;

public class MainUtil {
    private HibernateQuery hq = new HibernateQuery();


    public void addCity() {
        System.out.println("Enter city name: ");
        String s = new Scanner(System.in).nextLine();
        Capital capital =  new Capital();
        capital.setName(s);
        hq.pushCapital(capital);
    }

    public void addCountry() {
        System.out.println("Enter country name: ");
        String countryName = new Scanner(System.in).nextLine();
        Country country = new Country();
        country.setName(countryName);
        System.out.println("Choose capital (0 for empty capital cell): ");
        printCapitalList();
        int f = new Scanner(System.in).nextInt();
            country.setCapital(getCapitalById(f));
            hq.pushCountry(country);
    }

    public void updateCountry(){
        System.out.println("Select country you want to update");
        printCountryList();
        int k = new Scanner(System.in).nextInt();
        Country temp = getCountryById(k);
        System.out.println("1 - change country name \n2 - change capital");
        int z = new Scanner(System.in).nextInt();
        switch (z){
            case 1:
                System.out.println("Enter new name for country " + temp.getName());
                String newName = new Scanner(System.in).nextLine();
                temp.setName(newName);
                hq.updateObj(temp);
                break;
            case 2:
                System.out.println("1 - choose existing capital \n2 - set a new one");
                int p = new Scanner(System.in).nextInt();
                switch (p){
                    case 1:
                        printCapitalList();
                    {
                        int s = new Scanner(System.in).nextInt();
                        temp.setCapital(getCapitalById(s));
                        System.out.println("New capital of " + temp.getName() + " is now " + temp.getCapital().getName());
                        hq.updateObj(temp);
                    }
                    break;
                    case 2:
                        System.out.println("Enter city name: ");
                        String s = new Scanner(System.in).nextLine();
                        Capital capital =  new Capital();
                        capital.setName(s);
                        hq.pushCapital(capital);
                        temp.setCapital(capital);
                        System.out.println("New capital of " + temp.getName() + " is now " + temp.getCapital().getName());
                        hq.updateObj(temp);
                        break;
                    default:
                        System.out.println("Incorrect input");
                }
        }
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
            if (country.getId() == id) {
                return country;
            }
        }
        return null;
    }

    public void delete(){
        System.out.println("1 - delete country\n2 - delete capital: \n");
        int k = new Scanner(System.in).nextInt();
        switch (k){
            case 1:{
                System.out.println("Select country: \n");
                printCountryList();
                int f = new Scanner(System.in).nextInt();
                hq.removeObj(getCountryById(f));
            }
                break;
            case 2:{
                System.out.println("Select capital: \n");
                printCapitalList();
                int f = new Scanner(System.in).nextInt();
                hq.removeObj(getCapitalById(f));
            }
            break;
            default:
                System.out.println("Incorrect input");
        }
    }


}
