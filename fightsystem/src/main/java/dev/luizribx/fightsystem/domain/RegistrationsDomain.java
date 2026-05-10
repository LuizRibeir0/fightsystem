package dev.luizribx.fightsystem.domain;

import dev.luizribx.fightsystem.enums.StatusRegistrationsEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registrations")
public class RegistrationsDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_subscription")
    private LocalDate dateSubscription;

    @Column(name = "due_date")
    private Integer dateDue;

    @Column(name = "closing_date")
    private LocalDate dateClosing;

    @Enumerated(EnumType.STRING)
    private StatusRegistrationsEnum  status = StatusRegistrationsEnum.ATIVA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studant_id")
    private StudentsDomain students;

    @PrePersist
    public void prePersist(){
        if (this.dateSubscription == null) {
            this.dateSubscription = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSubscription() {
        return dateSubscription;
    }

    public void setDateSubscription(LocalDate dateSubscription) {
        this.dateSubscription = dateSubscription;
    }

    public Integer getDateDue() {
        return dateDue;
    }

    public void setDateDue(Integer dateDue) {
        this.dateDue = dateDue;
    }

    public LocalDate getDateClosing() {
        return dateClosing;
    }

    public void setDateClosing(LocalDate dateClosing) {
        this.dateClosing = dateClosing;
    }

    public StatusRegistrationsEnum getStatus() {
        return status;
    }

    public void setStatus(StatusRegistrationsEnum status) {
        this.status = status;
    }

    public StudentsDomain getStudents() {
        return students;
    }

    public void setStudents(StudentsDomain students) {
        this.students = students;
    }
}
