package novi.backend;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone();

    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;

        printInstructions();
        while(!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> mobilePhone.printContactList();
                case 2 -> addContact();
                case 3 -> updateContact();
                case 4 -> deleteContact();
                case 5 -> searchForContact();
                case 6 -> quit = true;
            }
        }
    }

    private static void printInstructions() {
        System.out.println("\n Press:");
        System.out.println("\t 0. To print choice options");
        System.out.println("\t 1. To print list of contacts");
        System.out.println("\t 2. To add new contact");
        System.out.println("\t 3. To update existing contact");
        System.out.println("\t 4. To delete contact");
        System.out.println("\t 5. To search contact");
        System.out.println("\t 6. To quit the application");
    }

    private static void addContact() {
        System.out.println("Enter new contact name: ");
        String contactName = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Contacts newContact = Contacts.createContact(contactName, phoneNumber);
        if(mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added: " + contactName + " - " + phoneNumber);
        } else {
            System.out.println("Contact already exists.");
        }
    }

    public static void updateContact() {
        System.out.println("Enter the name of the contact you want to update: ");
        String oldContactName = scanner.nextLine();

        Contacts oldContact = mobilePhone.queryContacts(oldContactName);

        if(oldContact == null) {
            System.out.println("Could not find contact.");
        } else {
            System.out.println("Enter updated contact name: ");
            String newContactName = scanner.nextLine();
            System.out.println("Enter updated contact phone number: ");
            String newPhoneNumber = scanner.nextLine();

            Contacts newContact = Contacts.createContact(newContactName, newPhoneNumber);
            mobilePhone.modifyContactList(oldContact, newContact);
        }
    }

    public static void deleteContact() {
        System.out.println("Enter the name of the contact you want to delete: ");
        String contactName = scanner.nextLine();

        Contacts contact = mobilePhone.queryContacts(contactName);
        if(contact == null) {
            System.out.println("Could not find contact.");
        } else {
            mobilePhone.removeContact(contact);
        }
    }

    public static void searchForContact() {
        System.out.println("Enter contact name: ");
        String contactName = scanner.nextLine();

        Contacts contact = mobilePhone.queryContacts(contactName);

        if(contact == null) {
            System.out.println("Could not find contact.");
        } else {
            System.out.println(contact.getName() + " - " + contact.getPhoneNumber());
        }
    }
}
