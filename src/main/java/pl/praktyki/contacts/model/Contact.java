package pl.praktyki.contacts.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Contact {

    private @Id @GeneratedValue Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;

    public Contact() {}

    public Contact(String name, String surname, String mail, String phone) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname) && Objects.equals(mail, contact.mail) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, mail, phone);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
