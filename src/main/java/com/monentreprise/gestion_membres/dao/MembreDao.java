package com.monentreprise.gestion_membres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monentreprise.gestion_membres.model.Membre;


@Repository
public interface MembreDao extends JpaRepository<Membre, Integer> {

    Membre findByEmail(String email);

    Membre findById(int id);
         
}
