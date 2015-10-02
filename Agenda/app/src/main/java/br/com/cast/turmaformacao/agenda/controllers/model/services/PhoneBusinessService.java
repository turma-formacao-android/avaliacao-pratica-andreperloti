package br.com.cast.turmaformacao.agenda.controllers.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.PhoneRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class PhoneBusinessService {

    public PhoneBusinessService() {
    }

    public static List<Phone> findAll() {
        return PhoneRepository.getAll();
    }

    public static void save(Phone phone) {
        //Salvar no Banco LOCAL
        PhoneRepository.save(phone);

    }

    public static void delete(Phone phone) {
        PhoneRepository.delete(phone.getId());
    }

    public static Phone findContactById(Long id) {
        Phone contactById = PhoneRepository.getPhoneById(id);
        return contactById;
    }



}
