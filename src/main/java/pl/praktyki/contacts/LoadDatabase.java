package pl.praktyki.contacts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.praktyki.contacts.model.Contact;
import pl.praktyki.contacts.repository.ContactsRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ContactsRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Contact("Adam", "Nowak", "adam.nowak@mail.com", "123456789")));
            log.info("Preloading " + repository.save(new Contact("Piotr", "Kowalski", "piotr.kowalski@mail.com", "987654321")));
        };
    }
}
