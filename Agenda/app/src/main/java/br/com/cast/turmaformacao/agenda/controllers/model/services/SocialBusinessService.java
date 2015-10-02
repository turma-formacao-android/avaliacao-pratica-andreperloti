package br.com.cast.turmaformacao.agenda.controllers.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Social;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.SocialRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class SocialBusinessService {

    public SocialBusinessService() {
    }


    public static List<Social> findAll() {
        return SocialRepository.getAll();
    }

    public static void save(Social social) {
        //Salvar no Banco LOCAL
        SocialRepository.save(social);

    }

    public static void delete(Social social) {
        SocialRepository.delete(social.getId());
    }

    public static Social findContactById(Long id) {
        Social contactById = SocialRepository.getSocialById(id);
        return contactById;
    }

}
