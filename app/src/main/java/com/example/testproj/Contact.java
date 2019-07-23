package com.example.testproj;

public class Contact {

    private int Id;
    private String Name, Contact, Email;

    public Contact(int id, String name, String contact, String email) {
        Id = id;
        Name = name;
        Contact = contact;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}