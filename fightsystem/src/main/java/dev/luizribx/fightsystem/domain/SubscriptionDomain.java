package dev.luizribx.fightsystem.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "subscription")
public class SubscriptionDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean active = true;

    @Column(name = "monthly_value")
    private BigDecimal monthlyValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modalitie_id")
    private ModalitiesDomain modalities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthlyValue() {
        return monthlyValue;
    }

    public void setMonthlyValue(BigDecimal monthlyValue) {
        this.monthlyValue = monthlyValue;
    }

    public ModalitiesDomain getModalities() {
        return modalities;
    }

    public void setModalities(ModalitiesDomain modalities) {
        this.modalities = modalities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
