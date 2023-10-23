package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "title_rent")
@Table(name = "title_rent")
public class TitleRentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private TenantEntity tenant;
    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private OwnerEntity owner;

    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private PropertyEntity property;
    private double valueRent;
    private int contractLengthInMonths;
    private boolean active;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
