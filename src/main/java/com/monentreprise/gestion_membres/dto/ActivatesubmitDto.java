package com.monentreprise.gestion_membres.dto;

import lombok.Data;

@Data
public class ActivatesubmitDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumbr;
    private String password;
    private String confirmPassword;

}
