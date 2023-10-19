package br.com.devairon.backend.backend_my_rent.domain.enums;

public enum OccupationStatus {
    OCCUPIED("occupied"),
    UNOCCUPIED("unoccupied");

    private String status;

    OccupationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
