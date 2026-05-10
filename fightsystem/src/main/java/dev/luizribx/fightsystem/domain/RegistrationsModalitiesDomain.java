package dev.luizribx.fightsystem.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registrations_modalities")
public class RegistrationsModalitiesDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id")
    private RegistrationsDomain registrations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id")
    private ModalitiesDomain modalities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_id")
    private GraduationDomain graduation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private SubscriptionDomain subscription;

    @PrePersist
    public void prePersist() {
        if (startDate == null) {
            startDate = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RegistrationsDomain getRegistrations() {
        return registrations;
    }

    public void setRegistrations(RegistrationsDomain registrations) {
        this.registrations = registrations;
    }

    public ModalitiesDomain getModalities() {
        return modalities;
    }

    public void setModalities(ModalitiesDomain modalities) {
        this.modalities = modalities;
    }

    public GraduationDomain getGraduation() {
        return graduation;
    }

    public void setGraduation(GraduationDomain graduation) {
        this.graduation = graduation;
    }

    public SubscriptionDomain getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionDomain subscription) {
        this.subscription = subscription;
    }
}
