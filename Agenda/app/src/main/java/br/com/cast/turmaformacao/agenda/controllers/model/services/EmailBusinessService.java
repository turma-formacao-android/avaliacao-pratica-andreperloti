package br.com.cast.turmaformacao.agenda.controllers.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Email;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.EmailRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class EmailBusinessService {
    public EmailBusinessService() {
    }


    public static List<Email> findAll() {
        return EmailRepository.getAll();
    }

    public static void save(Email email) {
        EmailRepository.save(email);

    }

    public static void delete(Email selectedContact) {
        EmailRepository.delete(selectedContact.getId());
    }

    public static Email findContactById(Long id) {
        Email contactById = EmailRepository.getEmailById(id);
        return contactById;
    }


}
