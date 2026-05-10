package dev.luizribx.fightsystem.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class AttendanceDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "leave_date")
    private LocalDateTime leaveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id")
    private RegistrationsDomain registrations;

    public void prePersist() {
        if (entryDate == null) {
            entryDate = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    public RegistrationsDomain getRegistrations() {
        return registrations;
    }

    public void setRegistrations(RegistrationsDomain registrations) {
        this.registrations = registrations;
    }
}
