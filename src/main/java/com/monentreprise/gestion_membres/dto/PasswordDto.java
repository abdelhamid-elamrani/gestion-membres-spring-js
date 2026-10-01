package com.monentreprise.gestion_membres.dto;

import lombok.Data;

@Data
public class PasswordDto {
    
    private String oldPassword;
    private String password;
    private String confirmPassword;
    
}
