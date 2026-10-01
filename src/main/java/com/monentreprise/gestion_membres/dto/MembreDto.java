package com.monentreprise.gestion_membres.dto;

import com.monentreprise.gestion_membres.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MembreDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumbr;
    private Role role;
    
}
