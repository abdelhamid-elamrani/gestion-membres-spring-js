package com.monentreprise.gestion_membres.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monentreprise.gestion_membres.dao.MembreDao;
import com.monentreprise.gestion_membres.model.Membre;


@Slf4j
@Service
public class MembreService {

    @Autowired
    MembreDao membreDao;

    public Membre findByEmail(String email) {
        log.debug("Recherche membre avec email: {}", email);
        return membreDao.findByEmail(email);
    }

    public void save(Membre membre) {
        log.info("Sauvegarde du membre: {}", membre.getEmail());
        membreDao.save(membre);
    }

    public List<Membre> findAll() {
        log.debug("Récupération de tous les membres");
        List<Membre> membres = membreDao.findAll();
        log.info("Nombre de membres trouvés: {}", membres.size());
        return membres;
    }

    public Membre findById(int id) {
        log.debug("Recherche du membre avec ID: {}", id);
        Membre membre = membreDao.findById(id);
        if (membre == null) {
            log.warn("Membre avec ID {} introuvable", id);
        }
        return membre;
    }

    public void deleteById(int id) {
        log.warn("Tentative de suppression du membre avec ID: {}", id);
        membreDao.deleteById(id);
        log.info("Membre avec ID {} supprimé avec succès", id);
    }
    
}
