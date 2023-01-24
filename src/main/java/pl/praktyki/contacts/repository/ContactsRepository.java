package pl.praktyki.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.praktyki.contacts.model.Contact;

public interface ContactsRepository extends JpaRepository<Contact, Long> {
}
