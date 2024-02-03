package br.com.haircutappoitment.domain.enums;

public enum UserRole {
    USER("user"),
    BARBER("barber"),
    MANAGER("manager"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
