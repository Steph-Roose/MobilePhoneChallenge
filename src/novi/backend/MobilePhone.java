package novi.backend;


import java.util.ArrayList;

public class MobilePhone {
    private ArrayList<Contacts> contactList = new ArrayList<Contacts>();

    // Getter
    public ArrayList<Contacts> getContactList() {
        return contactList;
    }

    // Print contacts
    public void printContactList() {
        System.out.println("You have " + contactList.size() + " contacts");

        for(int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1) + ". " + contactList.get(i).getName() + " - Phone number: " + contactList.get(i).getPhoneNumber());
        }
    }

    // Add contacts
    public boolean addNewContact(Contacts contact) {
        if(findContact(contact.getName()) >= 0) {
            System.out.println("Contact is already on file");
            return false;
        }
        contactList.add(contact);
        System.out.println("Contact is added to your list.");
        return true;
    }

    // Modify contacts
    public boolean modifyContactList(Contacts oldContact, Contacts newContact) {
        int position = findContact(oldContact);

        if(position < 0) {
            System.out.println(oldContact.getName() + " was not found.");
            return false;
        }

        contactList.set(position, newContact);
        System.out.println(oldContact.getName() + " was replaced with " + newContact.getName());
        return true;
    }

    // Remove contacts
    public void removeContact(Contacts contact) {
        int position = findContact(contact);

        if(position < 0) {
            System.out.println(contact.getName() + " was not found.");
        } else {
            contactList.remove(contact);
            System.out.println(contact.getName() + " was removed from the contactlist.");
        }
    }

    // Find contacts
    private int findContact(Contacts contact) {
        return contactList.indexOf(contact);
    }

    private int findContact(String contactName) {
        for(int i = 0; i < contactList.size(); i++) {
            Contacts contact = contactList.get(i);
            if(contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contacts queryContacts(String contactName) {
        int position = findContact(contactName);

        if(position < 0) {
            return null;
        } else {
            Contacts contact = contactList.get(position);
            return contact;
        }
    }
}
