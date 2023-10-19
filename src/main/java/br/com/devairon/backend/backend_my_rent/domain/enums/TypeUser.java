package br.com.devairon.backend.backend_my_rent.domain.enums;

public enum TypeUser {
    OWNER("owner"),
    TENANT("tenant");

    private String userType;

    TypeUser(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
