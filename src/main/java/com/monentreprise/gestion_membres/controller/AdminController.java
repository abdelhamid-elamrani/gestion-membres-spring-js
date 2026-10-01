package com.monentreprise.gestion_membres.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monentreprise.gestion_membres.service.MembreService;


import com.monentreprise.gestion_membres.config.AppConstants;

import com.monentreprise.gestion_membres.dto.AdminCreateDto;
import com.monentreprise.gestion_membres.dto.AdminEditDto;
import com.monentreprise.gestion_membres.dto.MembreDto;
import com.monentreprise.gestion_membres.model.Membre;
import com.monentreprise.gestion_membres.model.Role;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private MembreService membreService;
    
    @GetMapping("/members")
    public ResponseEntity<List<MembreDto>> LstMembres() {
        List<Membre> lst = membreService.findAll();

        List<MembreDto> lstDto = new ArrayList<>();

        for(Membre mbr : lst){
            MembreDto mDto = MembreDto.builder()
                            .id(mbr.getId())
                            .firstName(mbr.getFirstName())
                            .lastName(mbr.getLastName())
                            .email(mbr.getEmail())
                            .phoneNumbr(mbr.getPhoneNumbr())
                            .role(mbr.getRole())
                            .build();
            lstDto.add(mDto);
        }

        return ResponseEntity.ok(lstDto);
        
    }


    @PutMapping("/member/{id}/edit")
    public ResponseEntity<String> edit(@PathVariable int id, @RequestBody AdminEditDto donnees ) {

        String phoneNumbr = donnees.getPhoneNumbr();
        String firstName = donnees.getFirstName();
        String lastName = donnees.getLastName();
        Role role = donnees.getRole();

        Pattern patternPhone = Pattern.compile(AppConstants.PHONE_REGEX);

        if (!patternPhone.matcher(phoneNumbr).matches()) {

            return ResponseEntity.badRequest().body("Erreur format téléphone invalide");
        }
        Pattern patternName = Pattern.compile(AppConstants.NAME_REGEX);
        if (!patternName.matcher(firstName).matches() || !patternName.matcher(lastName).matches()) {
            return ResponseEntity.badRequest().body(" Erreur format nom/prénom invalide !");
        }
        
        Membre mbr = membreService.findById(id);
        if (mbr == null) return ResponseEntity.notFound().build();

        mbr.setFirstName(firstName);
        mbr.setLastName(lastName);
        mbr.setPhoneNumbr(phoneNumbr);
        mbr.setRole(role);
        membreService.save(mbr);

        return ResponseEntity.ok("Modifié avec succès !");
        
    }
    
    
    @DeleteMapping("/member/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Membre mbr = membreService.findById(id);

        if (mbr == null) return ResponseEntity.notFound().build();

        membreService.deleteById(id);

        return ResponseEntity.ok().body("Membre supprimé avec succès !");
        
    }
    
    @PostMapping("/member/create")
    public ResponseEntity<Object> create(@RequestBody AdminCreateDto donnees) {
        
        String firstName = donnees.getFirstName();
        String lastName = donnees.getLastName();
        String email = donnees.getEmail();
        String phoneNumbr = donnees.getPhoneNumbr();
        String role = donnees.getRole();
        
        
        Pattern patternMail = Pattern.compile(AppConstants.EMAIL_REGEX);
        if (!patternMail.matcher(email).matches()) {
            return ResponseEntity.badRequest().body("Erreur format email invalide");
        }
        
        Pattern patternPhone = Pattern.compile(AppConstants.PHONE_REGEX);
        if (!patternPhone.matcher(phoneNumbr).matches()) {
            return ResponseEntity.badRequest().body("Erreur format téléphone invalide");
        }
        
        Pattern patternName = Pattern.compile(AppConstants.NAME_REGEX);
        if (!patternName.matcher(firstName).matches() || !patternName.matcher(lastName).matches()) {

            return ResponseEntity.badRequest().body("Erreur format nom/prénom invalide");
        }

        if (membreService.findByEmail(email) != null) {

            return ResponseEntity.badRequest().body("Email déjà utilisé !");
        }
        
        Membre new_member = new Membre();
        new_member.setFirstName(firstName);
        new_member.setLastName(lastName);
        new_member.setEmail(email);
        new_member.setPhoneNumbr(phoneNumbr);
        
        String hash = encoder.encode(AppConstants.DEFAULT_PASSWORD);
        new_member.setPaswordHash(hash);
        
        if (role.equals("ADMIN")) {
            new_member.setRole(Role.ADMIN);
        } else {
            new_member.setRole(Role.CLIENT);
        }
        membreService.save(new_member);
        
        return ResponseEntity.ok().body("Membre créé avec succès !");
    }
}
