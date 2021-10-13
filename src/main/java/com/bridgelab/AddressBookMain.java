/*
 * @author: ASIM AHAMMED
 */

package com.bridgelab;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookMain {
    public static Scanner sc = new Scanner(System.in);
    public static List<PersonInfo> personInfo = new ArrayList<>();
    public static Map<String, List<PersonInfo>> hashmap = new HashMap<>();

    // Created a method for adding Contacts
    public static void addContact() {
        System.out.print("Enter Your First Name : ");
        String firstName = sc.next();
        System.out.print("Enter Your Last Name : ");
        String lastName = sc.next();
        if (checkExist(firstName, lastName, personInfo)) {
            System.out.println("Record already exists cant add !!!");
            menu();
        } else
            System.out.print("Enter Your Address : ");
        String address = sc.next();
        System.out.print("Enter Your City : ");
        String city = sc.next();
        System.out.print("Enter Your State : ");
        String state = sc.next();
        System.out.print("Enter Your Zip Code : ");
        String zipCode = sc.next();
        System.out.print("Enter Your Email : ");
        String email = sc.next();
        System.out.print("Enter Your Phone Number : ");
        String phoneNo = sc.next();
        PersonInfo person = new PersonInfo(firstName, lastName, address, city, state, zipCode, email, phoneNo);
        personInfo.add(person);
        for (PersonInfo personInfo2 : personInfo) {
            System.out.println(personInfo2);
        }
    }

    // UC4 Delete PersonInfo Method
    public static void deleteContact() {
        System.out.println("Enter first name for Delete PersonInfo : ");
        String firstName = sc.nextLine();
        for (int index = 0; index < personInfo.size(); index++) {
            if (personInfo.get(index).getFirstName().equalsIgnoreCase(firstName)) { // Comparing Two Strings
                personInfo.remove(index); // REMOVING ARRAY
                System.out.println("PersonInfo Deleted...");
            } else {
                System.out.println(firstName + " : No PersonInfo found in Address Book");
            }
        }
    }

    public static void showContact() {
        personInfo.forEach(System.out::println);
    }

    // UC3 editContact
    static void editContact() {
        String enteredName;
        System.out.println("Enter First name of contact to edit it ");
        enteredName = sc.next();
        for (PersonInfo info : personInfo) {
            if (info.getFirstName().equals(enteredName)) {
                int check = 0;
                System.out.println("Person found , what do you want to edit ?");
                System.out.println(
                        "Enter\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
                check = sc.nextInt();
                switch (check) {
                    case 1:
                        System.out.println("Enter new first name");
                        info.setFirstName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter new last name");
                        info.setLastName(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter new Address");
                        info.setAddress(sc.nextLine());
                        break;
                    case 4:
                        System.out.println("Enter new city");
                        info.setCity(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("Enter new state");
                        info.setState(sc.nextLine());
                        break;
                    case 6:
                        System.out.println("Enter new zip");
                        info.setZip(sc.nextLine());
                        break;
                    case 7:
                        System.out.println("Enter new phone number");
                        info.setPhNo(sc.nextLine());
                        break;
                    case 8:
                        System.out.println("Enter new email");
                        info.setEmailId(sc.nextLine());
                        break;
                    default:
                        System.out.println("Invalid Entry");
                }
            } else {
                System.out.println("Person Not Found");
            }
        }

    }
    /*
     * UC7 Ability to ensure there is no Duplicate Entry of the same Person
     */
    private static boolean checkExist(String firstName, String lastName, List<PersonInfo> personInfo) {
        boolean result = false;
        for (PersonInfo check : personInfo) {
            if (firstName.equals(check.getFirstName()) && lastName.equals(check.getLastName())) {
                result = true;
            }
        }
        return result;
    }

    /*
     *UC8
     *Ability to search Person in a City or State across the multiple AddressBook
     */
    public static void searchPersonByName(String firstName) {
        List listPerson = (List) personInfo.stream()
                .filter(p -> p.getFirstName().equals(firstName))
                .collect(Collectors.toList());
        listPerson.forEach(System.out::println);
    }

    /*
     *UC9
     *Ability to search Person in a City or State across the multiple AddressBook
     *:: -> Method Reference
     */
    public static void searchPersonByCity(String City) {
        List listPerson = personInfo.stream()
                .filter(p -> p.getCity().equals(City))
                //filtering Data
                .collect(Collectors.toList());
        listPerson.forEach(System.out::println);
    }

    /*
     *UC10 Ability to search Person in a City or State
     */
    public static void getCountByCity(String city) {
        List listPerson = personInfo.stream()
                .filter(p -> p.getCity().equals(city))
                //filtering Data
                .collect(Collectors.toList());
        //Collecting as a list
        long total = 0;
        total = Stream.of(listPerson).count();
        System.out.println("Totally " + total + " contacts present in the AddressBook");
    }
    /*
     * UC11
     * Ability to sort the entries in the address book alphabetically by Person’s name
     */
    /*
     * UC12
     * Ability to sort the entries in the address book by City, State, or Zip
     */
    public static void sortAddressBook() {
        System.out.println("1.Sort By Name\n2.Sort By City Name\n3.Sort By State\n4.Sort By Zip");
        int menuoption = sc.nextInt();
        switch (menuoption) {
            case 1:
                sortByFirstName();
                break;
            case 2:
                sortByCity();
            case 3:
                sortByState();
            case 4:
                sortByZip();
        }

    }

    public static void sortByFirstName() {
        System.out.println("\nSorting Address Book based on First Name");
        personInfo.sort(Comparator.comparing(PersonInfo::getFirstName));
        personInfo.forEach((PersonInfo contact) -> System.out.println(contact));
    }

    public static void sortByCity() {
        System.out.println("\nSorting Address Book based on First Name");
        personInfo.sort(Comparator.comparing(PersonInfo::getCity));
        personInfo.forEach((PersonInfo contact) -> System.out.println(contact));
    }

    public static void sortByState() {
        System.out.println("\nSorting Address Book based on First Name");
        personInfo.sort(Comparator.comparing(PersonInfo::getState));
        personInfo.forEach((PersonInfo contact) -> System.out.println(contact));
    }

    public static void sortByZip() {
        System.out.println("\nSorting Address Book based on First Name");
        personInfo.sort(Comparator.comparing(PersonInfo::getZip));
        personInfo.forEach((PersonInfo contact) -> System.out.println(contact));
    }

    public static void menu() {
        String menuOption;
        do {
            System.out.println("    1.Add Contact");
            System.out.println("	2.Edit Contact");
            System.out.println("	3.Delete Contact");
            System.out.println("	4.Show Contact");
            System.out.println("	5.Search Person Using Name");
            System.out.println("	6.Search Person Using City");
            System.out.println("	7.Number Of Contact Person");
            System.out.println("	8.Sort Address Book");
            System.out.println("	9.Save In Text File");
            System.out.println("	10.Read From Text File");
            System.out.println("	0.Exit");
            menuOption = sc.nextLine();
            switch (menuOption) {
                case "1":
                    addContact();
                    break;
                case "2":
                    editContact();
                    break;
                case "3":
                    deleteContact();
                    break;
                case "4":
                    showContact();
                    break;
                case "5":
                    System.out.println("Enter First Name");
                    String firstname = sc.next();
                    searchPersonByName(firstname);
                    break;
                case "6":
                    System.out.println("Enter City Name");
                    String city = sc.next();
                    searchPersonByCity(city);
                    break;
                case "7":
                    System.out.println("Enter City Name");
                    String cityCount = sc.next();
                    getCountByCity(cityCount);
                    break;
                case "8":
                    sortAddressBook();
                    break;
                case "9":
                    FileIO fileIO = new FileIO();
                    try {
                        fileIO.writeFile(personInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case "10":
                    FileIO fileIORead = new FileIO();
                    try {
                        fileIORead.readFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (menuOption.equals("0") == false);

    }

    // main method
    public static void main(String[] args) {
        System.out.println("Welcome To AddressBook");
        menu();
    }
}