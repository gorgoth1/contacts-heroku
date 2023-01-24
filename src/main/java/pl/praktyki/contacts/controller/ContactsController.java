package pl.praktyki.contacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.praktyki.contacts.model.Contact;
import pl.praktyki.contacts.repository.ContactsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContactsController {

    @Autowired
    private ContactsRepository contactsRepository;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        try {
            List<Contact> contacts = new ArrayList<>();

            contactsRepository.findAll().forEach(contacts::add);

            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") long id) {
        Optional<Contact> contactData = contactsRepository.findById(id);

        if (contactData.isPresent()) {
            return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        try {
            Contact newContact = contactsRepository
                    .save(new Contact(contact.getName(), contact.getSurname(), contact.getMail(), contact.getPhone()));
            return new ResponseEntity<>(newContact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
        Optional<Contact> contactData = contactsRepository.findById(id);

        if (contactData.isPresent()) {
            Contact existingContact = contactData.get();
            existingContact.setName(contact.getName());
            existingContact.setSurname(contact.getSurname());
            existingContact.setMail(contact.getMail());
            existingContact.setPhone(contact.getPhone());
            return new ResponseEntity<>(contactsRepository.save(existingContact), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") long id) {
        try {
            contactsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts")
    public ResponseEntity<HttpStatus> deleteAllContacts() {
        try {
            contactsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
