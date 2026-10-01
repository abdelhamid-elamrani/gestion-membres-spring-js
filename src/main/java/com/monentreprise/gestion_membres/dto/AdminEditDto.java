package com.monentreprise.gestion_membres.dto;

import com.monentreprise.gestion_membres.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminEditDto {
    
    private String firstName;
    private String lastName;
    private String phoneNumbr;
    private Role role;
    

}
