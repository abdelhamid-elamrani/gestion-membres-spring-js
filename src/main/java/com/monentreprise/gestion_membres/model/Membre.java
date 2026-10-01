package com.monentreprise.gestion_membres.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    
    private String paswordHash;
    private String phoneNumbr;

    @Enumerated(jakarta.persistence.EnumType.STRING) //stocke "CLIENT" ou "ADMIN" en texte dans la BD
    private Role role;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateInscription;
}
