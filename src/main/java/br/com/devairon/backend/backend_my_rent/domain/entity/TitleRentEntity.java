package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_titulo_aluguel")
public class TitleRentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    private TenantEntity tenant;
    @OneToOne
    private OwnerEntity owner;
    @OneToOne
    private AddressEntity address;
    @OneToOne
    private PropertyEntity property;
    private double valueRent;
    private int contractLengthInMonths;
    private boolean active;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
