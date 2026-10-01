package com.monentreprise.gestion_membres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.monentreprise.gestion_membres.dto.EditDto;
import com.monentreprise.gestion_membres.dto.MembreDto;
import com.monentreprise.gestion_membres.dto.PasswordDto;
import com.monentreprise.gestion_membres.model.Membre;
import com.monentreprise.gestion_membres.service.MembreService;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:8080")
public class ClientController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    MembreService membreService;


    @GetMapping("/profile/{id}")
    public ResponseEntity<MembreDto> profile(@PathVariable int id) {
        Membre membre = membreService.findById(id);
        if (membre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
            MembreDto.builder()
            .email(membre.getEmail())
            .firstName(membre.getFirstName())
            .lastName(membre.getLastName())
            .phoneNumbr(membre.getPhoneNumbr())
            .role(membre.getRole())
            .build()
        );
    }


    @PutMapping("/profile/{id}")
    public ResponseEntity<String> edit(@PathVariable int id,@RequestBody EditDto donnees){
        
        String firstName = donnees.getFirstName();
        String phoneNumbr = donnees.getPhoneNumbr();
        String lastName = donnees.getLastName();

        Pattern patternPhone = Pattern.compile(com.monentreprise.gestion_membres.config.AppConstants.PHONE_REGEX);
        Matcher matcherPhone = patternPhone.matcher(phoneNumbr);
        boolean bPhone = matcherPhone.matches();


        Pattern patternFstName = Pattern.compile(com.monentreprise.gestion_membres.config.AppConstants.NAME_REGEX);
        Matcher matcherFstName = patternFstName.matcher(firstName);
        boolean bFstName = matcherFstName.matches();

        Pattern patternSndName = Pattern.compile(com.monentreprise.gestion_membres.config.AppConstants.NAME_REGEX);
        Matcher matcherSndName = patternSndName.matcher(lastName);
        boolean bSndName = matcherSndName.matches();

            
        if (!bFstName ) {
            return ResponseEntity.badRequest().body("Format prénom invalide");
        }else if (!bSndName){
            return ResponseEntity.badRequest().body("Format nom invalide");
        }
        else if (!bPhone) {
            return ResponseEntity.badRequest().body("Format téléphone invalide");
        }
         else {
            Membre membre = membreService.findById(id);
            if(membre == null) return ResponseEntity.notFound().build();
            membre.setFirstName(firstName);
            membre.setLastName(lastName);
            membre.setPhoneNumbr(phoneNumbr);
            
            membreService.save(membre);

            return ResponseEntity.ok().body("Profil modifié avec succès");
        }

    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> password(@PathVariable int id, @RequestBody PasswordDto donnees){
        
        String oldPassword = donnees.getOldPassword();
        String password = donnees.getPassword();
        String confirmPassword = donnees.getConfirmPassword();

        Pattern patterPassword = Pattern.compile(com.monentreprise.gestion_membres.config.AppConstants.PASSWORD_REGEX);
        Matcher matcherPassword = patterPassword.matcher(password);
        boolean bPassword = matcherPassword.matches() && password.equals(confirmPassword);

        Membre membre = membreService.findById(id);

        if(membre == null) return ResponseEntity.notFound().build();

        if (! encoder.matches(oldPassword, membre.getPaswordHash())){
                
            return ResponseEntity.badRequest().body("Ancien mot depasse incorrect !");
        }

        if (!bPassword){
            return ResponseEntity.badRequest().body("Error de confirmation de mot de passe !");
        } 
        else if (encoder.matches(password, membre.getPaswordHash())){
            return ResponseEntity.badRequest().body("Meme mot de passe que l'ancien !");
        }
        else{
            String new_Hash = encoder.encode(password);

            membre.setPaswordHash(new_Hash);

            membreService.save(membre);

            return ResponseEntity.ok().body("Mot de passe modifié avec succès !");  
        }

    }
}


