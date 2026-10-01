package com.monentreprise.gestion_membres.config;

public final class AppConstants {
    
    private AppConstants() {}
    
    // MOTS DE PASSE
    public static final String DEFAULT_PASSWORD = "Membre2026!";
    
    //Expressions régulières
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
    public static final String PHONE_REGEX = "^0[1-9][0-9]{8}$";
    public static final String NAME_REGEX = "^[A-Za-zÀ-ÿ\s'-]+$";

    // ROUTES
    public static final String ROUTE_LOGIN = "redirect:/Auth/login";
    public static final String ROUTE_CLIENT_DASHBOARD = "redirect:/Client/dashboard";
    public static final String ROUTE_ADMIN_MEMBERS = "redirect:/admin/members";
    public static final String ROUTE_ADMIN_DASHBOARD = "redirect:/admin/dashboard";
    public static final String ROUTE_CLIENT_PASSWORD = "redirect:/Client/password";
}