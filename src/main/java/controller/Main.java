package controller;

import entities.Country;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainUtil mu = new MainUtil();
        while (true) {
            System.out.println("1 - create Element\n2 - read Elements \n3 - update elements \n4 - delete elements");
            int z = new Scanner(System.in).nextInt();
            switch (z) {
                case 1: {
                    System.out.println("1 - add new capital\n2 - add new country");
                    int k = new Scanner(System.in).nextInt();
                    switch (k) {
                        case 1: {
                            mu.addCity();
                            break;
                        }
                        case 2: {
                            mu.addCountry();
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("1 - get Mixed List\n2 - get capital list\n3 - get country list\n 4 - find capital by id");
                    int f = new Scanner(System.in).nextInt();
                    switch (f) {
                        case 1:
                            mu.printMixedList();
                            break;
                        case 2:
                            mu.printCapitalList();
                            break;
                        case 3:
                            mu.printCountryList();
                            break;
                        case 4: {
                            int k = new Scanner(System.in).nextInt();
                            mu.findCapitalById(k);
                        }
                    }
                    break;
                }
                case 3:
                    mu.updateCountry();
                    break;
                case 4:
                    mu.delete();
                    break;
                default:
                    System.out.println("Incorrect input");

            }
        }
    }
}
