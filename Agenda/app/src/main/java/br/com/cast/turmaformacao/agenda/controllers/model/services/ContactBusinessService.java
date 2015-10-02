package br.com.cast.turmaformacao.agenda.controllers.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.ContactRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ContactBusinessService {

    public ContactBusinessService() {
    }

    public static List<Contact> findAll() {
        return ContactRepository.getAll();
    }

    public static void save(Contact contact) {
        //Salvar no Banco LOCAL
        ContactRepository.save(contact);

    }

    public static void delete(Contact selectedContact) {
        ContactRepository.delete(selectedContact.getId());
    }

    public static Contact findContactById(Long id) {
        Contact contactById = ContactRepository.getContactById(id);
        return contactById;
    }

}
