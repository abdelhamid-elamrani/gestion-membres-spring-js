package com.monentreprise.gestion_membres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.monentreprise.gestion_membres.config.AppConstants;
import com.monentreprise.gestion_membres.dto.ActivatesubmitDto;
import com.monentreprise.gestion_membres.dto.LoginDto;
import com.monentreprise.gestion_membres.dto.MembreDto;
import com.monentreprise.gestion_membres.model.Membre;
import com.monentreprise.gestion_membres.model.Role;
import com.monentreprise.gestion_membres.service.MembreService;



@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    MembreService membreService;

    @GetMapping("/")
    public ResponseEntity<Void> afficherLogin() {
        return ResponseEntity
                .status(302)
                .header("Location", "/login.html")
                .build();
    }

    @PostMapping("/loginsubmit")
    public ResponseEntity<MembreDto> auth(@RequestBody LoginDto donnees) {

        String email = donnees.getEmail(); 
        String password = donnees.getPassword();
        
        Membre membre = membreService.findByEmail(email);

        if (membre == null) {
                return ResponseEntity.status(401).build();
        } else {
            //matches(motDePasseEnClair, hashStocké)
            if (encoder.matches(password, membre.getPaswordHash())) {
                return ResponseEntity.ok(
                    MembreDto.builder()
                            .id(membre.getId())
                            .firstName(membre.getFirstName())
                            .lastName(membre.getLastName())
                            .email(membre.getEmail())
                            .phoneNumbr(membre.getPhoneNumbr())
                            .role(membre.getRole())
                            .build()
                );
            } else {
                return ResponseEntity.status(401).build();
            }
        }
    }



    @PostMapping("/activatesubmit")
    public ResponseEntity<String> ActivateSubmit(@RequestBody ActivatesubmitDto donnees) {

        String firstName = donnees.getFirstName();
        String lastName = donnees.getLastName();
        String email = donnees.getEmail();
        String phoneNumbr = donnees.getPhoneNumbr();
        String password = donnees.getPassword();
        String confirmPassword = donnees.getConfirmPassword();

        Pattern patternMail = Pattern.compile(AppConstants.EMAIL_REGEX);
        Matcher matcherMail = patternMail.matcher(email);
        boolean b = matcherMail. matches();

        Pattern patternPhone = Pattern.compile(AppConstants.PHONE_REGEX);
        Matcher matcherPhone = patternPhone.matcher(phoneNumbr);
        boolean bPhone = matcherPhone. matches();

        Pattern patternFstName = Pattern.compile(AppConstants.NAME_REGEX);
        Matcher matcherFstName = patternFstName.matcher(firstName);
        boolean bFstName = matcherFstName.matches();

        Pattern patternSndName = Pattern.compile(AppConstants.NAME_REGEX);
        Matcher matcherSndName = patternSndName.matcher(lastName);
        boolean bSndName = matcherSndName.matches();

        Pattern patterPassword = Pattern.compile(AppConstants.PASSWORD_REGEX);
        Matcher matcherPassword = patterPassword.matcher(password);
        boolean bPassword = matcherPassword.matches();

        if (!b) {

            return ResponseEntity.badRequest().body("Format d'email invalide");
        }

        if (!bFstName) {
            
            return ResponseEntity.badRequest().body("Erreur format prénom !");
        }

        if (!bSndName) {

            return ResponseEntity.badRequest().body("Erreur format nom !");
        }

        if (!bPhone ) {
            
            return ResponseEntity.badRequest().body("Erreur format téléphone !");
        }

        if (!bPassword) {
            
            return ResponseEntity.badRequest().body("Erreur mot de passe invalide !");
        }

        if (!password.equals(confirmPassword))  {
            
            return ResponseEntity.badRequest().body("Erreur : les mots de passe ne correspondent pas !");
        }

        if (membreService.findByEmail(email) != null) {

            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        Membre membre = new Membre();
        /* On Hash le password avant de le stocker dans la base de données. */
        String hash = encoder.encode(password);

        membre.setFirstName(firstName);
        membre.setLastName(lastName);
        membre.setEmail(email);
        membre.setPhoneNumbr(phoneNumbr);
        membre.setPaswordHash(hash);
        membre.setRole(Role.CLIENT);
        membreService.save(membre);

        return ResponseEntity.ok("Compte activé avec succès !");
    }

}
