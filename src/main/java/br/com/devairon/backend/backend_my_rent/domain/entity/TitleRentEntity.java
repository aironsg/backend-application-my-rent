package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;

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
    private AddressPropertyEntity addressProperty;
    private double valueRent;
    private int contractLengthInMonths;
    private boolean active;
}
